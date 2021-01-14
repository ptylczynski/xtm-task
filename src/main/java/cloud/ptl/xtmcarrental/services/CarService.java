package cloud.ptl.xtmcarrental.services;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import cloud.ptl.xtmcarrental.dao.ClientDAO;
import cloud.ptl.xtmcarrental.dto.CarDTO;
import cloud.ptl.xtmcarrental.dto.ClientDTO;
import cloud.ptl.xtmcarrental.exceptions.CarAlreadyRented;
import cloud.ptl.xtmcarrental.exceptions.CarNotRented;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import cloud.ptl.xtmcarrental.repository.CarRepository;
import cloud.ptl.xtmcarrental.utils.CheckExistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ClientService clientService;

    public CarService(){};

    public CarDAO findById(Long id) throws ObjectDoesNotExists {
        Optional<CarDAO> carDAOOptional = this.carRepository.findById(id);
        CheckExistence.of(carDAOOptional);
        return carDAOOptional.get();
    }

    public List<CarDAO> findAll(){
        return (List<CarDAO>) this.carRepository.findAll();
    }

    public CarDAO save(CarDAO carDAO){
        if(carDAO.getDistanceCovered() == null) carDAO.setDistanceCovered(0L);
        return this.carRepository.save(carDAO);
    }

    public void deleteCar(CarDAO carDAO){
        this.carRepository.delete(carDAO);
    }

    public CarDAO rentCar(ClientDAO clientDAO, CarDAO carDAO) throws CarAlreadyRented {
        if(carDAO.getIsRented()) throw new CarAlreadyRented(carDAO.getNumberPlate());
        clientDAO.getCarsRented().add(carDAO);
        this.clientService.save(clientDAO);
        carDAO.setIsRented(true);
        return this.save(carDAO);
    }
    public CarDAO rentCar(ClientDTO clientDTO, CarDTO carDTO) throws ObjectDoesNotExists, CarAlreadyRented {
        return this.rentCar(
                this.clientService.findById(clientDTO.getId()),
                this.findById(carDTO.getId())
        );
    }

    public CarDAO returnCar(ClientDAO clientDAO, CarDAO carDAO, Long distance) throws CarNotRented {
        if(!carDAO.getIsRented()) throw new CarNotRented(carDAO.getNumberPlate());
        clientDAO.getCarsRented().remove(carDAO);
        this.clientService.save(clientDAO);
        carDAO.setIsRented(false);
        carDAO.setDistanceCovered(
                carDAO.getDistanceCovered() + distance
        );
        return this.save(carDAO);
    }

    public CarDAO returnCar(ClientDTO clientDTO, CarDTO carDTO, Long distance) throws ObjectDoesNotExists, CarNotRented {
        return this.returnCar(
                this.clientService.findById(clientDTO.getId()),
                this.findById(carDTO.getId()),
                distance
        );
    }
}
