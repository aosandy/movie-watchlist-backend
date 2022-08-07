package backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkRequest {
    private Long movieId;
    private String name;
    private String posterUrlPreview;
}
