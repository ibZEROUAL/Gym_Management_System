package ma.enset.gym_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.entities.Exercise;
import ma.enset.gym_management.enums.ProgramLevel;
import ma.enset.gym_management.enums.ProgramType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgramDto {
    private Long id;
    private String nom;
    private ProgramLevel niveau;
    private String description;
    private int dureeEnSemaines;
    private boolean visibilite;
    private String objectif;
    private ProgramType type;
    private Collection<ExerciseDto> exerciseDto= new ArrayList<>();
}
