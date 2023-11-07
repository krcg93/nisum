package project.nisum.infrastructure.shared.utils;

import io.micronaut.context.annotation.Property;

import java.util.UUID;

public class UuidUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
