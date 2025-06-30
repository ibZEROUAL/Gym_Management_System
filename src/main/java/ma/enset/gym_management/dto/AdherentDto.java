package ma.enset.gym_management.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class AdherentDto {
    @NotNull(message = "Le nom est obligatoire.")
    @Size(min = 4, message = "Le nom doit contenir au moins 4 caractères.")
    private String nom;

    private String email;


    private String password;


}
