package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
public class CoachingSession {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateInscriptionSession;
    private String statute;

    @ManyToOne
    private Adherent adherent;

    @ManyToOne
    private Coach coach;

}
