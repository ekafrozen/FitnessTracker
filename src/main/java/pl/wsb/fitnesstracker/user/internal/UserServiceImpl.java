package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.api.UserUpdateDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long userId, UserUpdateDto updateDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (updateDto.firstName() != null) user.setFirstName(updateDto.firstName());
        if (updateDto.lastName() != null) user.setLastName(updateDto.lastName());
        if (updateDto.birthdate() != null) user.setBirthdate(updateDto.birthdate());
        if (updateDto.email() != null) user.setEmail(updateDto.email());

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsersByEmailFragment(String emailFragment) {
        return userRepository.searchByEmailFragment(emailFragment);
    }

    @Override
    public List<User> searchUsersOlderThan(int age) {
        LocalDate dateLimit = LocalDate.now().minusYears(age);
        return userRepository.searchByBirthdateBefore(dateLimit);
    }
}