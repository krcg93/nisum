package project.nisum.infrastructure;

import io.micronaut.context.annotation.Factory;

import project.nisum.infrastructure.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class AppContext {

    @Inject
    UserService userService;

    @Singleton
    project.nisum.domain.service.UserService userServiceDomain() {
        return new project.nisum.domain.service.UserService(this.userService);
    }

}
