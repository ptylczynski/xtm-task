package cloud.ptl.xtmcarrental.converters.assemblers;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import cloud.ptl.xtmcarrental.dto.CarDTO;
import cloud.ptl.xtmcarrental.dto.RegisterCarDTO;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import cloud.ptl.xtmcarrental.services.CarService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CarDAOAssembler {
    @Autowired
    private CarService carService;

    public CarDAO toDAO(CarDTO carDTO) throws ObjectDoesNotExists {
        return CarDAO.builder()
                .id(carDTO.getId())
                .distanceCovered(carDTO.getDistanceCovered())
                .numberPlate(carDTO.getNumberPlate())
                .model(carDTO.getModel())
                .isRented(carDTO.getIsRented())
                .build();
    }

    public CarDAO toDAO(RegisterCarDTO registerCarDTO){
        return CarDAO.builder()
                .numberPlate(registerCarDTO.getNumberPlate())
                .model(registerCarDTO.getModel())
                .isRented(false)
                .build();
    }
}
