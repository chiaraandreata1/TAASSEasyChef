package EasyChef4.repositories;

import EasyChef4.models.Chef;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChefRepository extends CrudRepository<Chef, Long> {
    Optional<Chef> findById(Long id);
}
