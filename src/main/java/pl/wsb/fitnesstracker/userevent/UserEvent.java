package pl.wsb.fitnesstracker.userevent; // <-- Poprawiona nazwa pakietu

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.event.Event; // <-- Import klasy Event
import pl.wsb.fitnesstracker.user.api.User; // <-- Import klasy User

import java.time.LocalDate;

@Entity
@Table(name = "user_event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacja użytkownika
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacja wydarzenia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    public UserEvent(User user, Event event, LocalDate registrationDate) {
        this.user = user;
        this.event = event;
        this.registrationDate = registrationDate;
    }
}