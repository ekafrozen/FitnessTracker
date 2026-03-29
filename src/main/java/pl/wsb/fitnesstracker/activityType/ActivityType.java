package pl.wsb.fitnesstracker.activityType;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Activity_Type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activityType", nullable = false, unique = true)
    private String name;

    public ActivityType(String name) {
        this.name = name;
    }
}