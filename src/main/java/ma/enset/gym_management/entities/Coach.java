package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @SuperBuilder

@DiscriminatorValue("COACH")

public class Coach extends User {

    private String specialite;

    @OneToMany(mappedBy = "coach" , fetch = FetchType.LAZY)
    private Collection<CoachingSession> coachingSessions = new ArrayList<>();

    @OneToMany(mappedBy = "coach" , fetch = FetchType.LAZY)
    private Collection<Program> programs = new ArrayList<>();


}
