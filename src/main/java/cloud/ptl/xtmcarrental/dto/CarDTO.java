package cloud.ptl.xtmcarrental.dto;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Relation(itemRelation = "car", collectionRelation = "cars")
public class CarDTO extends RepresentationModel<CarDTO> {
    @NotNull
    private Long id;

    @NotNull
    private String model;

    @NotNull
    private String numberPlate;

    @NotNull
    private Boolean isRented;

    private Long distanceCovered;
}
