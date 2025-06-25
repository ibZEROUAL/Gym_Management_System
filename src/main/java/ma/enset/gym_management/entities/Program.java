package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.enums.ProgramObjective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Program {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    @Enumerated(EnumType.STRING)
    private ProgramLevel niveau;

    private String description;

    @Min(1)
    @Max(52)
    private int dureeEnSemaines;

    private boolean visibilite;

    @Enumerated(EnumType.STRING)
    private ProgramObjective objective;

    @ManyToMany(mappedBy = "programs",fetch = FetchType.EAGER)
    private Collection<Exercise> exercises=new ArrayList<>();

    @ManyToMany(mappedBy = "programs",fetch = FetchType.EAGER)
    private Collection<Repast> repasts=new ArrayList<>();

    @ManyToOne
    private Coach coach;

    @OneToMany(mappedBy = "program")
    private Collection<RegistrationProgram> registrations = new ArrayList<>();


    // Redéfinir equals et hashCode par id uniquement
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Program other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}