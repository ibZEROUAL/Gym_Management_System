package ma.enset.gym_management.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoachDto {
    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 4, message = "Le nom doit contenir au moins 4 caractères.")
    private String nom;

    @NotNull(message = "Le username est obligatoire.")
    @Size(min = 4, message = "Le username doit contenir au moins 5 caractères.")
    private String email;

    private String password;

    private String specialite;


}
