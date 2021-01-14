package cloud.ptl.xtmcarrental.controllers;

import cloud.ptl.xtmcarrental.converters.assemblers.CarDAOAssembler;
import cloud.ptl.xtmcarrental.converters.assemblers.CarDTOAssembler;
import cloud.ptl.xtmcarrental.dto.CarDTO;
import cloud.ptl.xtmcarrental.dto.ClientDTO;
import cloud.ptl.xtmcarrental.dto.RegisterCarDTO;
import cloud.ptl.xtmcarrental.exceptions.CarAlreadyRented;
import cloud.ptl.xtmcarrental.exceptions.CarNotRented;
import cloud.ptl.xtmcarrental.exceptions.MissingOrWrongField;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import cloud.ptl.xtmcarrental.services.CarService;
import cloud.ptl.xtmcarrental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarDAOAssembler carDAOAssembler;

    @Autowired
    private CarDTOAssembler carDTOAssembler;

    @GetMapping("/{id}")
    public EntityModel<CarDTO> getCar(
            @PathVariable("id") Long id
    ) throws ObjectDoesNotExists {
        return EntityModel.of(
                this.carDTOAssembler.toModel(
                        this.carService.findById(id)
                ),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getCar(id)).withSelfRel()
        );
    }

    @GetMapping("/")
    public CollectionModel<CarDTO> getAllCars(){
            return CollectionModel.of(
                    this.carDTOAssembler.toCollectionModel(
                        this.carService.findAll()
                    ),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).getAllCars()).withSelfRel()
            );
    }

    @PatchMapping("/rent/{carId}")
    public EntityModel<CarDTO> rentCar(
            @PathVariable("carId") Long carId,
            @RequestParam("clientId") Long clientId
    ) throws ObjectDoesNotExists, CarAlreadyRented {
        return EntityModel.of(
                this.carDTOAssembler.toModel(
                        this.carService.rentCar(
                                this.clientService.findById(clientId),
                                this.carService.findById(carId)
                        )
                ),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).rentCar(carId, clientId)).withSelfRel()
        );
    }

    @PatchMapping("/return/{carId}")
    public EntityModel<CarDTO> returnCar(
            @PathVariable("carId") Long carId,
            @RequestParam("clientId") Long clientId,
            @RequestParam("distanceCovered") Long distanceCovered
    ) throws ObjectDoesNotExists, CarNotRented {
        return EntityModel.of(
                this.carDTOAssembler.toModel(
                        this.carService.returnCar(
                            this.clientService.findById(clientId),
                            this.carService.findById(carId),
                                distanceCovered
                    )
                ),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CarController.class).returnCar(carId, clientId, distanceCovered)).withSelfRel()
        );
    }

    @PostMapping
    public EntityModel<CarDTO> postCar(
            @Valid RegisterCarDTO registerCarDTO,
            BindingResult bindingResult
    ) throws MissingOrWrongField {
        if(bindingResult.hasErrors())
            throw new MissingOrWrongField(
                    bindingResult.getFieldErrors().get(0).getField()
            );
        return EntityModel.of(
                this.carDTOAssembler.toModel(
                        this.carService.save(
                                this.carDAOAssembler.toDAO(registerCarDTO)
                        )
                ),
                WebMvcLinkBuilder.linkTo(CarController.class).withSelfRel()
        );
    }
}
