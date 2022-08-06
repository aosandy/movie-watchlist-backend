package backend.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG =
        "user with email %s not found";

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        return repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String registerUser(User user) {
        boolean userExists = repository
            .findByEmail(user.getEmail())
            .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        repository.save(user);

        String token = UUID.randomUUID().toString();

/*
        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            appUser
        );

        confirmationTokenService.saveConfirmationToken(
            confirmationToken);
*/

//        TODO: SEND EMAIL

        return token;
    }

    public User loginUser(User user) {
        boolean userExists = repository
            .findByEmail(user.getEmail())
            .isPresent();

        if (userExists) {
            return user;

        }
        throw new UsernameNotFoundException("User not found");
    }



    public int enableUser(String email) {
        return repository.enableAppUser(email);
    }
}
