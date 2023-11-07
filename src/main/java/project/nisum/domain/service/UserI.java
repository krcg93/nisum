package project.nisum.domain.service;

import project.nisum.domain.model.users.UserRequest;
import project.nisum.domain.model.users.UserResponse;
import reactor.core.publisher.Mono;

public interface UserI {
    Mono<UserResponse> createUser(UserRequest userRequest);
}
