package backend.auth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static backend.security.SecurityUtils.getCurrentlyAuthenticatedUsername;

@CrossOrigin(origins = "https://movie-watchlist-frontend-app.herokuapp.com/")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/reg")
    public String register(@Valid @RequestBody RegistrationRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(@RequestBody LoginRequest request){
        service.logout(request);
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUsername(){
        return new ResponseEntity<>(getCurrentlyAuthenticatedUsername(), HttpStatus.OK);
    }
}
