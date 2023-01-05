package EasyChef4.repositories;

import EasyChef4.models.Chef;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChefRepository extends CrudRepository<Chef, Long> {
}
