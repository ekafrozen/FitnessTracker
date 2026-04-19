package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training; // Upewnij się, że importujesz poprawny pakiet

import java.time.LocalDateTime;

@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacja wielu do jednego (wiele sesji do jednego treningu)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Współrzędne GPS (możesz dopasować typy do swoich potrzeb, np. Double)
    @Column(name = "start_lat")
    private Double startLat;

    @Column(name = "start_lng")
    private Double startLng;

    @Column(name = "end_lat")
    private Double endLat;

    @Column(name = "end_lng")
    private Double endLng;

    private Double altitude;

    public WorkoutSession(Training training, LocalDateTime timestamp, Double startLat, Double startLng, Double endLat, Double endLng, Double altitude) {
        this.training = training;
        this.timestamp = timestamp;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
        this.altitude = altitude;
    }
}