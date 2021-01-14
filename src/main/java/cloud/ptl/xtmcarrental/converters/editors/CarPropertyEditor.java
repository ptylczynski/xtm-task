package cloud.ptl.xtmcarrental.converters.editors;

import cloud.ptl.xtmcarrental.converters.assemblers.CarDTOAssembler;
import cloud.ptl.xtmcarrental.dao.CarDAO;
import cloud.ptl.xtmcarrental.services.CarService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CarPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CarService carService;

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @SneakyThrows
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        CarDAO carDAO = this.carService.findById(Long.valueOf(text));
        setValue(new CarDTOAssembler().toModel(carDAO));
    }
}
