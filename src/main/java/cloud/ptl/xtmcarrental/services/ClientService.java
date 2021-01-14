package cloud.ptl.xtmcarrental.services;

import cloud.ptl.xtmcarrental.dao.ClientDAO;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import cloud.ptl.xtmcarrental.repository.ClientRepository;
import cloud.ptl.xtmcarrental.utils.CheckExistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientService(){};

    public ClientDAO findById(Long id) throws ObjectDoesNotExists {
        Optional<ClientDAO> clientDAOOptional = this.clientRepository.findById(id);
        CheckExistence.of(clientDAOOptional);
        return clientDAOOptional.get();
    }

    public void createClient(ClientDAO clientDAO){
        this.clientRepository.save(clientDAO);
    }

    public ClientDAO save(ClientDAO clientDAO){
        return this.clientRepository.save(clientDAO);
    }
}
