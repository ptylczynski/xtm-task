package cloud.ptl.xtmcarrental.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clients")
@Table(name = "clients")
public class ClientDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String password;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "client"
    )
    private List<CarDAO> carsRented;
}
