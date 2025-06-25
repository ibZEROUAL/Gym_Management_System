package ma.enset.gym_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.enums.ExerciseCategorie;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ExerciseResponseDto {
    private Long id;
    private String nom;
    private String description;
    private Date duree;
    private ExerciseCategorie categorie;
    private String image_exercise;
}
