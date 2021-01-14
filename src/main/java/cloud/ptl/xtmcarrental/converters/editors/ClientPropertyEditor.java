package cloud.ptl.xtmcarrental.converters.editors;

import cloud.ptl.xtmcarrental.converters.assemblers.ClientDTOAssembler;
import cloud.ptl.xtmcarrental.dao.ClientDAO;
import cloud.ptl.xtmcarrental.repository.ClientRepository;
import cloud.ptl.xtmcarrental.services.ClientService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class ClientPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDTOAssembler clientDTOAssembler;

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @SneakyThrows
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        ClientDAO clientDAO = this.clientService.findById(Long.valueOf(text));
        setValue(this.clientDTOAssembler.toModel(clientDAO));
    }
}
