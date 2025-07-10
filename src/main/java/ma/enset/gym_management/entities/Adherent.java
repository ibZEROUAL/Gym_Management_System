package ma.enset.gym_management.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.enset.gym_management.enums.Role;


import java.util.ArrayList;
import java.util.Collection;


@Entity

@Getter @Setter
@SuperBuilder
@DiscriminatorValue("ADHERENT")
public class Adherent extends User {


    public Adherent() {
        this.setRole(Role.ADHERENT);
    }

    public Adherent(Subscription subscription, Collection<RegistrationProgram> registrations, Collection<CoachingSession> coachingSessions) {
        this.subscription = subscription;
        this.registrations = registrations;
        this.coachingSessions = coachingSessions;
    }

    public Adherent(Long id, String nom, @Email @NotBlank String email, String password, Role role, Subscription subscription, Collection<RegistrationProgram> registrations, Collection<CoachingSession> coachingSessions) {
        super(id, nom, email, password, role);
        this.subscription = subscription;
        this.registrations = registrations;
        this.coachingSessions = coachingSessions;
    }

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    Subscription subscription;

    @OneToMany(mappedBy = "adherent",fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<RegistrationProgram> registrations = new ArrayList<>();

    @OneToMany(mappedBy = "adherent", fetch = FetchType.LAZY)
    private Collection<CoachingSession> coachingSessions = new ArrayList<>();








}
