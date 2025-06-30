package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.enums.SubscriptionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private SubscriptionType type;

    @Column(nullable = false)
    private double prix;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;


    @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY)
    private Collection<Adherent> adherents = new ArrayList<>();

}
