package ma.enset.gym_management.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ma.enset.gym_management.enums.RepastObjictive;
import ma.enset.gym_management.enums.RepastType;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class Repast {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nom;
    @Enumerated(EnumType.STRING)
    private RepastType type;
    @Enumerated(EnumType.STRING)
    private RepastObjictive objective;

    private int calories;
    private int proteines;
    private int glucides;
    private int lipides;
    private int fibres;

    private String image; // URL vers une image

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Program> programs = new ArrayList<>();

    @OneToMany(mappedBy = "repast", fetch = FetchType.EAGER)
    private Collection<Aliment> aliments = new ArrayList<>();



}
