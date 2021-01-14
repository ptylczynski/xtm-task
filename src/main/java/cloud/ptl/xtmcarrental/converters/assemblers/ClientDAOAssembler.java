package cloud.ptl.xtmcarrental.converters.assemblers;

import cloud.ptl.xtmcarrental.dao.ClientDAO;
import cloud.ptl.xtmcarrental.dto.ClientDTO;
import cloud.ptl.xtmcarrental.dto.RegisterDTO;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import cloud.ptl.xtmcarrental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDAOAssembler {
    @Autowired
    private ClientService clientService;

    public ClientDAO toDAO(ClientDTO clientDTO) {
        return ClientDAO.builder()
                .id(clientDTO.getId())
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .build();
    }

    public ClientDAO toDAO(RegisterDTO registerDTO) {
        return ClientDAO.builder()
                .firstName(registerDTO.getFirstName())
                .lastName(registerDTO.getLastName())
                .password(registerDTO.getPassword())
                .build();

    }
}
