package backend.service;

import backend.mapper.MovieMapper;
import backend.mapper.MoviePreviewMapper;
import backend.model.Movie;
import backend.model.MoviePreview;
import backend.model.dto.MovieDto;
import backend.model.dto.SearchMovieDto;
import backend.user.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityManager;
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
    private final MovieMapper movieMapper;
    private final MoviePreviewMapper moviePreviewMapper;

    public List<MoviePreview> getPremieres() {
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
                    List<MovieDto> raw = objectMapper.readValue(s.traverse(), new TypeReference<>() {} );
                    return raw.stream().map(moviePreviewMapper::movieDtoToMoviePreview).toList();
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ArrayList<MoviePreview>();
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
            .map(movieMapper::movieDtoToMovie)
            .block();
    }

    public List<MoviePreview> getMoviesByKeyword(String keyword) {
        String uri = String.join("", "v2.1/films/search-by-keyword?keyword=", keyword, "&page=1");
        return webClient
            .get()
            .uri(uri)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(s -> s.path("films"))
            .map(s -> {
                try {
                    List<SearchMovieDto> raw = objectMapper.readValue(s.traverse(), new TypeReference<List<SearchMovieDto>>() {} );
                    return raw.stream().map(moviePreviewMapper::searchMovieDtoToMoviePreview).toList();
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ArrayList<MoviePreview>();
                }
            })
            .block();
    }
}
