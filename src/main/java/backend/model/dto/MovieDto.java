package backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieDto {

    private Long kinopoiskId;

    private String nameRu;

    private String nameEn;

    private Integer year;

    private String posterUrl;

    private String posterUrlPreview;
}
