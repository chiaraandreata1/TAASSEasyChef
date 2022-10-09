package userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import userservice.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long idUser);
}
