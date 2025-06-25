package ma.enset.gym_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Coach;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CoachingSessionResponseDto{

    private Long id;

    private LocalDateTime dateInscriptionSession;
    private String statute;

    private Adherent adherent;

    private Coach coach;
}
