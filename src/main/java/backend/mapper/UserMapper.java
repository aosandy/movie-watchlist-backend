package backend.mapper;

import backend.payload.RegistrationRequest;
import backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User registrationRequestToUser(RegistrationRequest source);
}
