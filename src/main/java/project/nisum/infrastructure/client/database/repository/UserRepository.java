package project.nisum.infrastructure.client.database.repository;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import project.nisum.infrastructure.client.database.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Executable
    Boolean existsByEmail(String email);

    @Executable
    UserEntity findByEmail(String email);

    @Executable
    Boolean existsByName(String name);

    /*@Query("update UserEntity ue set ue.lastlogin = ?1 where ue.name = ?2")
    public void updateLastLoginUsername(String lastLogin, String username);*/
}
