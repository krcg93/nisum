package project.nisum.application;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import project.nisum.infrastructure.shared.dto.users.PhonesInformationDto;
import project.nisum.infrastructure.shared.dto.users.UserRequestDto;
import project.nisum.infrastructure.shared.dto.users.UserResponseDto;
import reactor.test.StepVerifier;

import javax.inject.Inject;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class UserAcceptance {

    @Inject
    @Client("/users-services")
    HttpClient client;

    @Test
    public void saveUserOK() {
        StepVerifier.create(client.retrieve(HttpRequest.POST("/users", getUser()), UserRequestDto.class))
                .consumeNextWith(userResponse -> {
                    assertNotNull(userResponse);
                }).expectComplete()
                .verify();
    }

    @Test
    public void saveUserError() {
        StepVerifier.create(client.retrieve(HttpRequest.POST("/usr", getUser()), UserResponseDto.class))
                .expectError()
                .verify();
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
}
