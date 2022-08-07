package backend.model.entity;

import backend.model.Movie;
import backend.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MovieMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long movieId;

    private String name;

    private String posterUrlPreview;

    public MovieMark(User user, Long movieId, String name, String posterUrlPreview) {
        this.user = user;
        this.movieId = movieId;
        this.name = name;
        this.posterUrlPreview = posterUrlPreview;
    }
}
