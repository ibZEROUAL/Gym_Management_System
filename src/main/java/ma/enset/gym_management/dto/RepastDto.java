package ma.enset.gym_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.enums.RepastObjective;
import ma.enset.gym_management.enums.RepastType;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RepastDto {
    private String nom;
    private RepastType type;
    private RepastObjective objective;

    private int calories;
    private int proteines;
    private int glucides;
    private int lipides;
    private int fibres;

    private String image;

    //private Collection<AlimentDto> alimentsDto = new ArrayList<>();


}
