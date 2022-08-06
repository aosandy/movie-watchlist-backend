package backend.mapper;

import backend.auth.RegistrationRequest;
import backend.model.Movie;
import backend.model.dto.MovieDto;
import backend.model.dto.SearchMovieDto;
import backend.user.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User registrationRequestToUser(RegistrationRequest source);
}
