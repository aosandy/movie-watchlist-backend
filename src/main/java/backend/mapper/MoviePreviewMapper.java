package backend.mapper;

import backend.model.MoviePreview;
import backend.model.dto.MovieDto;
import backend.model.dto.SearchMovieDto;
import backend.model.entity.MovieMark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MoviePreviewMapper {

    @Mapping(source = "kinopoiskId", target = "id")
    @Mapping(source = "nameRu", target = "name")
    @Mapping(source = "posterUrlPreview", target = "posterUrlPreview")
    MoviePreview movieDtoToMoviePreview(MovieDto source);

    @Mapping(source = "filmId", target = "id")
    @Mapping(source = "nameRu", target = "name")
    @Mapping(source = "posterUrlPreview", target = "posterUrlPreview")
    MoviePreview searchMovieDtoToMoviePreview(SearchMovieDto source);

    @Mapping(source = "movieId", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "posterUrlPreview", target = "posterUrlPreview")
    MoviePreview movieMarkToMoviePreview(MovieMark source);
}
