package cloud.ptl.xtmcarrental.converters.converter;

import cloud.ptl.xtmcarrental.dto.ClientDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientStringConverter implements Converter<ClientDTO, String> {
    @Override
    public String convert(ClientDTO clientDTO) {
        return clientDTO.getId().toString();
    }
}
