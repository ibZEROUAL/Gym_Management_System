package ma.enset.gym_management.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    /*private String firstname;
    private String lastname;*/
    private String nom;
    private String username;
    private String password;
}
