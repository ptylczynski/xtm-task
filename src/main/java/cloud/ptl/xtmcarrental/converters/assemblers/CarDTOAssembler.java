package cloud.ptl.xtmcarrental.converters.assemblers;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import cloud.ptl.xtmcarrental.dto.CarDTO;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CarDTOAssembler extends RepresentationModelAssemblerSupport<CarDAO, CarDTO> {
    public CarDTOAssembler() {
        super(CarDAO.class, CarDTO.class);
    }

    @Override
    public CarDTO toModel(CarDAO carDAO) {
        return CarDTO.builder()
                .id(carDAO.getId())
                .distanceCovered(carDAO.getDistanceCovered())
                .model(carDAO.getModel())
                .numberPlate(carDAO.getNumberPlate())
                .isRented(carDAO.getIsRented())
                .build();
    }
}
