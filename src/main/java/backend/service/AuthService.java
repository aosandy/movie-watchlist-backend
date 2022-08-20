package backend.service;

import backend.payload.LoginRequest;
import backend.payload.LoginResponse;
import backend.payload.RegistrationRequest;
import backend.security.jwt.JwtTokenProvider;
import backend.model.entity.User;
import backend.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

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
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            String jwt = jwtTokenProvider.createToken(authentication);
            User user = (User) authentication.getPrincipal();
            user = service.loginUser(user);

            return new LoginResponse(
                jwt,
                user.getId(),
                user.getUsernameField(),
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
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));


        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
