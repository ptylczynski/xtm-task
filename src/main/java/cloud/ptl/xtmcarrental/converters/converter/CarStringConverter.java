package cloud.ptl.xtmcarrental.converters.converter;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import cloud.ptl.xtmcarrental.dto.CarDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarStringConverter implements Converter<CarDTO, String> {
    @Override
    public String convert(CarDTO carDTO) {
        return carDTO.getId().toString();
    }
}
