package cloud.ptl.xtmcarrental.dto;

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
@Relation(itemRelation = "exception", collectionRelation = "exceptions")
public class ExceptionDTO extends RepresentationModel<ExceptionDTO> {
    @NotNull
    private String exception;

    @NotNull
    private String description;
}
