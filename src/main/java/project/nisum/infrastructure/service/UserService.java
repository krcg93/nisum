package project.nisum.infrastructure.service;

import org.modelmapper.ModelMapper;
import project.nisum.domain.exception.BusinessException;
import project.nisum.domain.exception.ExceptionFactory;
import project.nisum.domain.model.users.UserRequest;
import project.nisum.domain.model.users.UserResponse;
import project.nisum.domain.service.UserI;
import project.nisum.infrastructure.client.database.entity.UserEntity;
import project.nisum.infrastructure.client.database.repository.PhoneRepository;
import project.nisum.infrastructure.client.database.repository.UserRepository;
import project.nisum.infrastructure.shared.dto.users.UserRequestDto;
import project.nisum.infrastructure.shared.dto.users.UserResponseDto;
import project.nisum.infrastructure.shared.utils.UuidUtil;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
public class UserService implements UserI {

    @Inject
    private ModelMapper mapper;
    @Inject
    UserRepository userRepository;
    @Inject
    PhoneRepository phoneRepository;


    @Override
    public Mono<UserResponse> createUser(UserRequest userRequest) {
        return Mono.just(mapper.map(userRequest, UserRequestDto.class))
                .flatMap(userRequestDto -> {
                    return existEmail(userRequestDto);
                })
                .map(user -> {
                    return mapper.map(user, UserResponse.class);
                })
                .onErrorResume(error -> {
                    return Mono.error(ExceptionFactory.EXIST_EMAIL.get("email"));
                });
    }

    private Mono<UserResponseDto> existEmail(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new BusinessException(ExceptionFactory.EXIST_EMAIL.get("email").getMessage(), 400);
        } else {
            return save(userRequestDto);
        }
    }

    private Mono<UserResponseDto> save(UserRequestDto userRequestDto) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

        UserEntity userMap = mapper.map(userRequestDto, UserEntity.class);
        userMap.setToken(UuidUtil.getUuid());
        userMap.setModified(simpleDateFormat.format(new Date()));
        userMap.setCreated(simpleDateFormat.format(new Date()));
        userMap.setLastLogin(simpleDateFormat.format(new Date()));
        userMap.setActive(true);

        UserEntity userAfterSave = userRepository.save(userMap);

        userMap.getPhones().stream().forEach(phone -> {
            phone.setUser(userAfterSave);
            phoneRepository.save(phone);
        });

        return Mono.just(mapper.map(userAfterSave, UserResponseDto.class));
    }
}
