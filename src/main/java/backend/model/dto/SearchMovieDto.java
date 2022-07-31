package backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchMovieDto {

    private Long filmId;

    private String nameRu;

    private String posterUrlPreview;
}
