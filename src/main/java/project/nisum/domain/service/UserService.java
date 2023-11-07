package project.nisum.domain.service;

import project.nisum.domain.exception.Validate;
import project.nisum.domain.model.users.UserRequest;
import project.nisum.domain.model.users.UserResponse;
import reactor.core.publisher.Mono;

public class UserService {

    private UserI userI;

    public UserService(UserI userI) {
        this.userI = userI;
    }

    public Mono<UserResponse> createUser(UserRequest userRequest) {
        return Validate.nullEntityValidate(userRequest, "create user").switchIfEmpty(userRequest.validate())
                .then(Mono.defer(() -> {
                    return userI.createUser(userRequest);
                }))
                .onErrorResume(Mono::error);
    }

}
