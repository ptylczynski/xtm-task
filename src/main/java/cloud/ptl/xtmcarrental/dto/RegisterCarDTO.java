package cloud.ptl.xtmcarrental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Relation(itemRelation = "registeredCar")
public class RegisterCarDTO extends RepresentationModel<RegisterCarDTO> {
    @NotNull
    private String model;

    @NotNull
    private String numberPlate;
}
