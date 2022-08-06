package backend.mapper;

import backend.model.Movie;
import backend.model.dto.MovieDto;
import backend.model.dto.SearchMovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MovieMapper {

    @Mapping(source = "kinopoiskId", target = "id")
    @Mapping(source = "nameRu", target = "name")
    @Mapping(source = "posterUrl", target = "posterUrl")
    Movie movieDtoToMovie(MovieDto source);

    @Mapping(source = "filmId", target = "id")
    @Mapping(source = "nameRu", target = "name")
    @Mapping(source = "posterUrlPreview", target = "posterUrlPreview")
    Movie searchMovieDtoToMovie(SearchMovieDto source);
}
