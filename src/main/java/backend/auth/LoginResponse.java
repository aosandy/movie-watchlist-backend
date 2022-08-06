package backend.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private static final String type = "Bearer";

    private String accessToken;
    private Long id;
    private String username;
    private String email;
    private String role;
}
