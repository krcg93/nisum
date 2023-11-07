package project.nisum.domain.model.users;

import project.nisum.domain.exception.Validate;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;

public class UserRequest implements Serializable {
    private String name;
    private String email;
    private String password;
    private List<PhonesInformation> phones;

    public UserRequest() {
    }

    public Mono<Void> validate() {
        return Validate
                .nullOrEmptyValidate(name, "name")
                .switchIfEmpty(Validate.mailValidate(email, "email"))
                .switchIfEmpty(Validate.passwordValidate(password, "password"))
                .switchIfEmpty(Validate.nullOrEmptyValidate(phones, "phonesInformations"));
    }
}
