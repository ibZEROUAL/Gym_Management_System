package ma.enset.gym_management.dto;

import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.enums.SubscriptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionResponseDto {

    private Long id;

    private SubscriptionType type;

    private double prix;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Collection<AdherentResponseDTO> adherents = new ArrayList<>();

}
