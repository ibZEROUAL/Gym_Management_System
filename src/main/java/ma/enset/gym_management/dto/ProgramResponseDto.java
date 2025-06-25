package ma.enset.gym_management.dto;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.entities.Coach;
import ma.enset.gym_management.entities.RegistrationProgram;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.enums.ProgramObjective;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgramResponseDto {
    private Long id;
    private String nom;
    private ProgramLevel niveau;
    private String description;
    private int dureeEnSemaines;
    private boolean visibilite;
    private ProgramObjective objective;
    private Coach coach;
    private Collection<ExerciseResponseDto> exerciseResponseDto = new ArrayList<>();
    private Collection<RepastResponseDto> repastResponseDto = new ArrayList<>();
}
