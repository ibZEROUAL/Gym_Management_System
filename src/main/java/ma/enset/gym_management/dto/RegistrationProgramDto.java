package ma.enset.gym_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Program;


import java.sql.Date;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationProgramDto {

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime registeredAt;
    private AdherentDto adherent;
    private ProgramDto programs;

}
