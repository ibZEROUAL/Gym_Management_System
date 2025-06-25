package ma.enset.gym_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.entities.Adherent;
import ma.enset.gym_management.entities.Program;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationProgramResponseDto {
    private Long id;
    private LocalDateTime registeredAt;

    private AdherentDto adherent;

    private ProgramDto programs;

}
