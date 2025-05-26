package ma.enset.gym_management.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.enset.gym_management.enums.RepastObjictive;
import ma.enset.gym_management.enums.RepastType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RepastDto {
    private Long id;
    private String nom;
    private RepastType type;
    private RepastObjictive objective;

    private int calories;
    private int proteines;
    private int glucides;
    private int lipides;
    private int fibres;

    private String image;


}
