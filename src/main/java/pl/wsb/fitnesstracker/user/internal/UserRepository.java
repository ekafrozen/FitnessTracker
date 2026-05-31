package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

interface UserRepository extends JpaRepository<User, Long> {

    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    @Query(value = "SELECT * FROM users WHERE email LIKE %:domain", nativeQuery = true)
    List<User> findUsersByEmailDomain(@Param("domain") String domain);

    default List<User> searchByEmailFragment(String fragment) {
        String lowerCaseFragment = fragment.toLowerCase();
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(lowerCaseFragment))
                .collect(Collectors.toList());
    }

    default List<User> searchByBirthdateBefore(LocalDate dateLimit) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(dateLimit))
                .collect(Collectors.toList());
    }
}