package backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MoviePreview {

    private Long id;

    private String name;

    private String posterUrlPreview;
}
