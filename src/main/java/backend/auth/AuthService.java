package backend.auth;

import backend.security.jwt.JwtTokenProvider;
import backend.user.User;
import backend.user.UserRole;
import backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String register(RegistrationRequest request) {
        String token = service.registerUser(
            new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER
            )
        );

/*
        String link = "http://localhost:8080/auth/confirm?token=" + token;
        emailSender.send(
            request.getEmail(),
            buildEmail(request.getFirstName(), link));
*/

        return token;
    }

    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String jwt = jwtTokenProvider.createToken(authentication);
            User user = (User) authentication.getPrincipal();
            user = service.loginUser(user);

            return new LoginResponse(
                jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getUserRole().name()
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public void logout(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
