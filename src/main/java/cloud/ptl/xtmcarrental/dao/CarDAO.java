package cloud.ptl.xtmcarrental.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cars")
@Table(name = "cars")
public class CarDAO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String numberPlate;
    private Long distanceCovered;
    private Boolean isRented;

    @ManyToOne
    private ClientDAO client;
}
