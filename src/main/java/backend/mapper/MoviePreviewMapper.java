package backend.mapper;

import backend.model.MoviePreview;
import backend.model.dto.MovieDto;
import backend.model.dto.SearchMovieDto;
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
}
