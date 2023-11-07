package project.nisum.domain.model.users;

import project.nisum.domain.exception.Validate;
import reactor.core.publisher.Mono;

public class PhonesInformation {
    private String number;
    private String citycode;
    private String contrycode;

    public PhonesInformation() {}

    private PhonesInformation(PhonesInformation phonesInformation) {
        this.number = phonesInformation.number;
        this.citycode = phonesInformation.citycode;
        this.contrycode = phonesInformation.contrycode;
    }

    public static Mono<PhonesInformation> create(PhonesInformation phonesInformation) {
        PhonesInformation phonesInformation1 = new PhonesInformation(phonesInformation);
        return phonesInformation1.validate().then(Mono.just(phonesInformation1));
    }

    public Mono<Void> validate() {
        return Validate
                .nullOrEmptyValidate(number, "number");
    }
}
