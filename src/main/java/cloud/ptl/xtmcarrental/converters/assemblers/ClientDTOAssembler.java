package cloud.ptl.xtmcarrental.converters.assemblers;

import cloud.ptl.xtmcarrental.dao.ClientDAO;
import cloud.ptl.xtmcarrental.dto.ClientDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ClientDTOAssembler extends RepresentationModelAssemblerSupport<ClientDAO, ClientDTO> {
    public ClientDTOAssembler() {
        super(ClientDAO.class, ClientDTO.class);
    }

    @Override
    public ClientDTO toModel(ClientDAO clientDAO) {
        return ClientDTO.builder()
                .id(clientDAO.getId())
                .firstName(clientDAO.getFirstName())
                .lastName(clientDAO.getLastName())
                .build();
    }
}
