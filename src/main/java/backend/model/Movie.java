package backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Movie {

    private Long id;

    private String name;

    private Integer year;

    private String posterUrl;

    private String posterUrlPreview;
}
