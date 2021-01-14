package cloud.ptl.xtmcarrental.dto;

import cloud.ptl.xtmcarrental.dao.ClientDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Relation(itemRelation = "client", collectionRelation = "clients")
public class ClientDTO extends RepresentationModel<ClientDTO> {

    @NotNull
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
