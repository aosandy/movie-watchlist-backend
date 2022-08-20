package backend.controller;

import backend.model.Movie;
import backend.model.MoviePreview;
import backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping("/premieres")
    public ResponseEntity<List<MoviePreview>> getPremieres() {
        return new ResponseEntity<>(service.getPremieres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getMovieById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MoviePreview>> getMoviesByKeyword(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(service.getMoviesByKeyword(keyword), HttpStatus.OK);
    }
}
