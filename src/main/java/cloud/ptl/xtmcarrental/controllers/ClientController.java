package cloud.ptl.xtmcarrental.controllers;

import cloud.ptl.xtmcarrental.converters.assemblers.ClientDAOAssembler;
import cloud.ptl.xtmcarrental.converters.assemblers.ClientDTOAssembler;
import cloud.ptl.xtmcarrental.dto.ClientDTO;
import cloud.ptl.xtmcarrental.dto.RegisterDTO;
import cloud.ptl.xtmcarrental.exceptions.MissingOrWrongField;
import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;
import cloud.ptl.xtmcarrental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@ExposesResourceFor(ClientDTO.class)
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDAOAssembler clientDAOAssembler;

    @Autowired
    private ClientDTOAssembler clientDTOAssembler;

    @GetMapping("{id}")
    public EntityModel<ClientDTO> getClient(
            @PathVariable("id") Long id
    ) throws ObjectDoesNotExists {
        return EntityModel.of(
                this.clientDTOAssembler.toModel(
                        this.clientService.findById(id)
                ),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).getClient(id))
                    .withSelfRel()
        );
    }

    @PostMapping
    public EntityModel<ClientDTO> postUser(
            @Valid RegisterDTO registerDTO,
            BindingResult bindingResult
    ) throws MissingOrWrongField, ObjectDoesNotExists {
        if(bindingResult.hasErrors()){
            throw new MissingOrWrongField("test");
        }
        return EntityModel.of(
            this.clientDTOAssembler.toModel(
                    this.clientService.save(
                        this.clientDAOAssembler.toDAO(registerDTO)
                    )
            ),
            WebMvcLinkBuilder.linkTo(ClientController.class).withSelfRel()
        );
    }
}
