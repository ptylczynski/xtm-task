package cloud.ptl.xtmcarrental.controllers;

import cloud.ptl.xtmcarrental.dto.ExceptionDTO;
import cloud.ptl.xtmcarrental.exceptions.CarAlreadyRented;
import cloud.ptl.xtmcarrental.exceptions.CarNotRented;
import cloud.ptl.xtmcarrental.exceptions.MissingOrWrongField;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ObjectDoesNotExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityModel<ExceptionDTO> handle(ObjectDoesNotExists ex){
        return EntityModel.of(
                ExceptionDTO.builder()
                    .exception(ex.getClass().getSimpleName())
                    .description(
                            "Object does not exists"
                    ).build(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExceptionHandlers.class).handle(ex)).withSelfRel()
        );
    }

    @ExceptionHandler(MissingOrWrongField.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityModel<ExceptionDTO> handle(MissingOrWrongField ex){
        return EntityModel.of(
                ExceptionDTO.builder()
                        .exception(ex.getClass().getSimpleName())
                        .description(
                                String.format("Field %s is missing", ex.getFieldName())
                        ).build(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExceptionHandlers.class).handle(ex)).withSelfRel()
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityModel<ExceptionDTO> handle(NoHandlerFoundException ex){
        return EntityModel.of(
                ExceptionDTO.builder()
                    .exception(ex.getClass().getSimpleName())
                    .description(
                            String.format(
                                    "Endpoint %s for method %s not found",
                                    ex.getRequestURL(),
                                    ex.getHttpMethod()
                            )
                    ).build(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExceptionHandlers.class).handle(ex)).withSelfRel()
        );
    }

    @ExceptionHandler(CarAlreadyRented.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityModel<ExceptionDTO> handle(CarAlreadyRented ex){
        return EntityModel.of(
                ExceptionDTO.builder()
                        .exception(ex.getClass().getSimpleName())
                        .description(
                                String.format(
                                        "Car with numberplate %s already rented",
                                        ex.getNumberPlace()
                                )
                        ).build(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExceptionHandlers.class).handle(ex)).withSelfRel()
        );
    }

    @ExceptionHandler(CarNotRented.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EntityModel<ExceptionDTO> handle(CarNotRented ex){
        return EntityModel.of(
                ExceptionDTO.builder()
                        .exception(ex.getClass().getSimpleName())
                        .description(
                                String.format(
                                        "Car with numberplate %s is not rented",
                                        ex.getNumberPlace()
                                )
                        ).build(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExceptionHandlers.class).handle(ex)).withSelfRel()
        );
    }
}
