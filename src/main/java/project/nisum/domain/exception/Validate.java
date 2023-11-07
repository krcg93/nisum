package project.nisum.domain.exception;

import io.micronaut.context.annotation.Property;
import project.nisum.infrastructure.shared.utils.Expression;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Validate {
    public static Mono<Void> nullOrEmptyValidate(String value, String field) {
        return Mono.justOrEmpty(value).filter(it-> (value!=null && !value.isEmpty()))
                .switchIfEmpty(Mono.error(ExceptionFactory.NULL_OR_EMPTY.get(field)))
                .onErrorResume(error -> Mono.error(error))
                .then(Mono.empty());
    }

    public static Mono<Void> nullOrEmptyValidate(List value, String field) {
        return Mono.justOrEmpty(value).filter(it-> (value!=null && !value.isEmpty()))
                .switchIfEmpty(Mono.error(ExceptionFactory.NULL_OR_EMPTY.get(field)))
                .onErrorResume(error -> Mono.error(error))
                .then(Mono.empty());
    }

    public static Mono<Void> nullEntityValidate(Object value, String field) {
        return Mono.justOrEmpty(value).filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(ExceptionFactory.NULL_ENTITY.get(field)))
                .onErrorResume(error -> Mono.error(error))
                .then(Mono.empty());
    }

    public static Mono<Void> mailValidate(String value, String field) {
        return Mono.just(Pattern.compile("^(.+)@(.+)$").matcher(value).matches())
                .filter(it-> it)
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_MAIL.get(field)))
                .then(Mono.empty());
    }

    public static Mono<Void> passwordValidate(String value, String field) {
        return Mono.just(Pattern.compile(Expression.passwordExpression()).matcher(value).matches())
                .filter(it-> it)
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_PASSWORD.get(field)))
                .then(Mono.empty());
    }
}
