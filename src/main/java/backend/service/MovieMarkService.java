package backend.service;

import backend.model.MarkRequest;
import backend.model.entity.MovieMark;
import backend.model.entity.User;
import backend.repository.MovieMarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieMarkService {
    private final UserService userService;
    private final MovieMarkRepository movieMarkRepository;

    public void markMovieForCurrentUser(MarkRequest request) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<MovieMark> markOptional = movieMarkRepository.findByUserAndMovieId(user, request.getMovieId());
        if (markOptional.isPresent()) {
            Long id = markOptional.get().getId();
            movieMarkRepository.save(
                new MovieMark(id, user, request.getMovieId(), request.getName(), request.getPosterUrlPreview()));
            return;
        }
        movieMarkRepository.save(
            new MovieMark(user, request.getMovieId(), request.getName(), request.getPosterUrlPreview()));
    }

    public void unmarkMovieForCurrentUser(Long movieId) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<MovieMark> markOptional = movieMarkRepository.findByUserAndMovieId(user, movieId);
        markOptional.ifPresent(movieMarkRepository::delete);
    }
}
