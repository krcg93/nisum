package project.nisum.application;

import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import project.nisum.domain.exception.ExceptionFactory;
import project.nisum.domain.model.users.UserRequest;
import project.nisum.domain.model.users.UserResponse;
import project.nisum.domain.service.UserI;
import project.nisum.domain.service.UserService;
import project.nisum.infrastructure.shared.dto.users.PhonesInformationDto;
import project.nisum.infrastructure.shared.dto.users.UserRequestDto;
import project.nisum.infrastructure.shared.dto.users.UserResponseDto;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.inject.Inject;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@MicronautTest
public class UserUnit {

    @Inject
    @Client("/users-services")
    RxHttpClient client;

    @Inject
    private ModelMapper mapper;

    UserI userI;
    UserService userService;

    @BeforeEach
    void IniTest() {
        userI = Mockito.mock(UserI.class);
        userService = new UserService(userI);
    }

    @Test
    void saveUserOk() {
        Mockito.when(userI.createUser(any(UserRequest.class))).then(item -> {
            return getUserResponse();
        });

        StepVerifier.create(userService.createUser(mapper.map(getUser(), UserRequest.class)))
                .consumeNextWith(user -> {
                    assertNotNull(user);
                    UserResponseDto userResponseDto = mapper.map(user, UserResponseDto.class);
                    assertEquals(true, userResponseDto.getActive());
                })
                .verifyComplete();
    }

    @Test
    void saveUserError() {
        Mockito.when(userI.createUser(any(UserRequest.class))).then(item -> {
            return Mono.error(new Exception());
        });

        StepVerifier.create(userService.createUser(mapper.map(getUser(), UserRequest.class)))
                .expectError()
                .verify();
    }

    @Test
    void saveUserErrorPassword() {
        StepVerifier.create(userService.createUser(mapper.map(getUserPasswordError(), UserRequest.class)))
                .verifyErrorMessage(ExceptionFactory.INVALID_PASSWORD.get("password").getMessage());
    }

    public Mono<UserResponse> getUserResponse() {
        UserResponseDto userResponse = new UserResponseDto("1", "11-07-2023", "11-07-2023", "11-07-2023", "aca2fb624c7a4f038045f9bfdf9b6245", true);
        return Mono.just(mapper.map(userResponse, UserResponse.class));
    }

    public UserRequestDto getUser() {
        UserRequestDto userRequestDto = new UserRequestDto();
        PhonesInformationDto phonesInformationDto = new PhonesInformationDto();

        userRequestDto.setName("kevin Chilito");
        userRequestDto.setEmail("kevin.chilito@icloud.com");
        userRequestDto.setPassword("ASFFAFD5a8ad$");

        phonesInformationDto.setNumber("3502788976");
        phonesInformationDto.setCitycode("1");
        phonesInformationDto.setContrycode("57");

        userRequestDto.setPhones(Arrays.asList(phonesInformationDto));

        return userRequestDto;
    }

    public UserRequestDto getUserPasswordError() {
        UserRequestDto userRequestDto = new UserRequestDto();
        PhonesInformationDto phonesInformationDto = new PhonesInformationDto();

        userRequestDto.setName("kevin Chilito");
        userRequestDto.setEmail("kevin.chilito@icloud.com");
        userRequestDto.setPassword("123445");

        phonesInformationDto.setNumber("3502788976");
        phonesInformationDto.setCitycode("1");
        phonesInformationDto.setContrycode("57");

        userRequestDto.setPhones(Arrays.asList(phonesInformationDto));

        return userRequestDto;
    }
}
