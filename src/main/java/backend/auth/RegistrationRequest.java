package backend.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotEmpty
    private String username;
    @Email
    private String email;
    @NotEmpty
    private String password;
}
