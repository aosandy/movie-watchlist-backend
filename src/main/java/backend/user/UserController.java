package backend.user;

import backend.model.MarkRequest;
import backend.model.MoviePreview;
import backend.model.entity.MovieMark;
import backend.service.MovieMarkService;
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
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MovieMarkService movieMarkService;

    @GetMapping("/user/marks")
    public ResponseEntity<List<MoviePreview>> getUserMarkedMovies() {
        return new ResponseEntity<>(userService.getUserMarkedMovies(), HttpStatus.OK);
    }

    @PostMapping("/user/marks")
    public void markMovieForCurrentUser(@RequestBody MarkRequest request) {
        movieMarkService.markMovieForCurrentUser(request);
    }

    @DeleteMapping("/user/marks/{id}")
    public void unmarkMovieForCurrentUser(@PathVariable("id") Long movieId) {
        movieMarkService.unmarkMovieForCurrentUser(movieId);
    }
}
