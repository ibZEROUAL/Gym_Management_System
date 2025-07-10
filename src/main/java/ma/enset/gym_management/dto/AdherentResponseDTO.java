package ma.enset.gym_management.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AdherentResponseDTO {
    private Long id;

    private String nom;

    private String email;

    private Collection<RegistrationProgramDto> registrations = new ArrayList<>();
}
