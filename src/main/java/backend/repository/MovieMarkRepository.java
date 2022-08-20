package backend.repository;

import backend.model.entity.MovieMark;
import backend.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieMarkRepository extends CrudRepository<MovieMark, Long> {
    Optional<MovieMark> findByUserAndMovieId(User user, Long movieId);
}
