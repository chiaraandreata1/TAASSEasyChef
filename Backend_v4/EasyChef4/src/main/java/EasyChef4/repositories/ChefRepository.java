package EasyChef4.repositories;

import EasyChef4.models.Chef;
import org.springframework.data.repository.CrudRepository;

public interface ChefRepository extends CrudRepository<Chef, Long> {
}
