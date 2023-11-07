package project.nisum.infrastructure.client.database.repository;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import project.nisum.infrastructure.client.database.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Executable
    Boolean existsByEmail(String email);
}
