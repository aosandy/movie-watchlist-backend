package backend.controller;

import backend.model.MarkRequest;
import backend.model.MoviePreview;
import backend.service.MovieMarkService;
import backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
