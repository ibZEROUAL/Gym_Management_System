package ma.enset.gym_management.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ma.enset.gym_management.enums.ExerciseCategorie;

import java.util.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder

public class Exercise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    @Column(nullable = false)
    private String description;

    private int repetitions;
    private int series;

    @Enumerated(EnumType.STRING)
    private ExerciseCategorie categorie;

    private String image_exercise;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Program> programs=new ArrayList<>();


    // Redéfinir equals et hashCode par id uniquement
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
