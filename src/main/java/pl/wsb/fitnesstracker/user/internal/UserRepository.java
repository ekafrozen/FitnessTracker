package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Dodany import
import org.springframework.data.repository.query.Param; // Dodany import
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List; // Dodany import
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    // --- ZADANIE 4: Natywne zapytanie SQL ---
    @Query(
            value = "SELECT * FROM users WHERE email LIKE %:domain",
            nativeQuery = true
    )
    List<User> findUsersByEmailDomain(@Param("domain") String domain);

}