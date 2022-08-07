package backend.service;

import backend.model.MarkRequest;
import backend.model.entity.MovieMark;
import backend.user.User;
import backend.repository.MovieMarkRepository;
import backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieMarkService {
    private final UserRepository userRepository;
    private final MovieMarkRepository movieMarkRepository;

    public void markMovieForCurrentUser(MarkRequest request) {
        User user = userRepository.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

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
        User user = userRepository.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<MovieMark> markOptional = movieMarkRepository.findByUserAndMovieId(user, movieId);
        markOptional.ifPresent(movieMarkRepository::delete);
    }
}
