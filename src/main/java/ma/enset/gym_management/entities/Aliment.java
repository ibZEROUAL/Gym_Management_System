package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Builder
public class Aliment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "repas_id")
    private Repast repast;


}
