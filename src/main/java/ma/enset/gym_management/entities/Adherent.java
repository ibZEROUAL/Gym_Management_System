package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.ArrayList;
import java.util.Collection;


@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@SuperBuilder
@DiscriminatorValue("ADHERENT")
public class Adherent extends User {


    @OneToMany(mappedBy = "adherent",fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<RegistrationProgram> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "adherent", fetch = FetchType.EAGER)
    private Collection<CoachingSession> coachingSessions = new ArrayList<>();


}
