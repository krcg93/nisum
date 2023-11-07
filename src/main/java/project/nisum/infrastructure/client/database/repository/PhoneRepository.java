package project.nisum.infrastructure.client.database.repository;

import io.micronaut.data.annotation.*;
import io.micronaut.data.jpa.repository.JpaRepository;
import project.nisum.infrastructure.client.database.entity.PhoneEntity;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

}
