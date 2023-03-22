package program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import program.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
