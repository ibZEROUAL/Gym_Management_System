package ma.enset.gym_management.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    private String nom;
    private String email;
    private String password;
}
