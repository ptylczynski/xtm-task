package cloud.ptl.xtmcarrental.repository;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDAO, Long> {
}
