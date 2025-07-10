package ma.enset.gym_management.entities;

import jakarta.persistence.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.enset.gym_management.enums.Role;


@SuperBuilder
@Getter
@Setter
@Entity
public class Admin extends User{

    public Admin() {
        this.setRole(Role.COACH);
    }

    public Admin(Long id, String nom, @Email @NotBlank String email, String password, Role role) {
        super(id, nom, email, password, role);
    }
}
