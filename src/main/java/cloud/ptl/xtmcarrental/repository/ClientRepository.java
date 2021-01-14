package cloud.ptl.xtmcarrental.repository;

import cloud.ptl.xtmcarrental.dao.ClientDAO;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientDAO, Long> {
}
