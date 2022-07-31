package backend.service;

import backend.mapper.MovieMapper;
import backend.model.Movie;
import backend.model.dto.MovieDto;
import backend.model.dto.SearchMovieDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final WebClient webClient;
    private ObjectMapper objectMapper;
    private final MovieMapper mapper;

    public List<Movie> getPremieres() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMMM dd, yyyy");
        LocalDateTime now = LocalDateTime.now();

        String uri = String.join("", "v2.2/films/premieres?year=2022&month=July");
        return webClient
            .get()
            .uri(uri)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(s -> s.path("items"))
            .map(s -> {
                try {
                    List<MovieDto> raw = objectMapper.readValue(s.traverse(), new TypeReference<List<MovieDto>>() {} );
                    return raw.stream().map(mapper::movieDtoToMovie).toList();
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ArrayList<Movie>();
                }
            })
            .block();
    }

    public Movie getMovieById(Long id) {
        String uri = String.join("", "v2.2/films/", id.toString());
        return webClient
            .get()
            .uri(uri)
            .retrieve()
            .bodyToMono(MovieDto.class)
            .map(mapper::movieDtoToMovie)
            .block();
    }
}
