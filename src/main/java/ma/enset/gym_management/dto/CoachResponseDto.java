package ma.enset.gym_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoachResponseDto {
    private Long id;
    private String nom;
    private String username;
    private String specialite;

    private Collection<ProgramResponseDto> programResponseDto = new ArrayList<>();
}
