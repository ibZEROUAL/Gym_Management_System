package ma.enset.gym_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.enums.SubscriptionType;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionDto {

    @NotNull(message = "Le type est obligatoire.")
    @Size(min = 4, message = "Le nom doit contenir au moins 4 caractères.")
    private SubscriptionType type;

    @NotNull(message = "Le prix est obligatoire.")
    private double prix;

    @NotNull(message = "La date de dubute est obligatoire.")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateDebut;


    @NotNull(message = "La date de fin est obligatoire.")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateFin;
}
