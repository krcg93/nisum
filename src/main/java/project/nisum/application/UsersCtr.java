package project.nisum.application;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import project.nisum.domain.model.users.UserRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import org.modelmapper.ModelMapper;
import project.nisum.domain.service.UserService;
import project.nisum.infrastructure.shared.dto.users.UserRequestDto;
import project.nisum.infrastructure.shared.dto.users.UserResponseDto;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

@Controller("/users-services")
public class UsersCtr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private UserService userService;

    @Post(value = "users", produces = MediaType.APPLICATION_JSON)
    public Mono<UserResponseDto> getUsers(@Body UserRequestDto userRequest) {
        return Mono.just(mapper.map(userRequest, UserRequest.class))
                .flatMap(userService::createUser)
                .map(user -> mapper.map(user, UserResponseDto.class));

    }
}
