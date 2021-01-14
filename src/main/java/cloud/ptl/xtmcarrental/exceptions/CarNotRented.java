package cloud.ptl.xtmcarrental.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarNotRented extends Exception{
    private String numberPlace;
}
