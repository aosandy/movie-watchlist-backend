package backend.auth;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String password;
}
