package cloud.ptl.xtmcarrental;

import cloud.ptl.xtmcarrental.dao.CarDAO;
import cloud.ptl.xtmcarrental.repository.CarRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    @Order(10)
    @Rollback
    void save_one_car(){
        CarDAO carDAO = this.carRepository.save(
                CarDAO.builder()
                    .model("dsgdfsgdf")
                    .numberPlate("hfgdfs")
                    .build()
        );

        assertEquals(carDAO.getModel(), "dsgdfsgdf");
        assertEquals(carDAO.getNumberPlate(), "hfgdfs");
    }

    @Test
    @Order(20)
    @Rollback
    void read_one_car(){
        CarDAO savedCarDAO = this.carRepository.save(
                CarDAO.builder()
                        .model("dsgdfsgdf")
                        .numberPlate("hfgdfs")
                        .build()
        );

        Optional<CarDAO> carDAO = this.carRepository.findById(savedCarDAO.getId());
        assertTrue(carDAO.isPresent());
        assertEquals(carDAO.get().getNumberPlate(), "hfgdfs");
        assertEquals(carDAO.get().getModel(), "dsgdfsgdf");
    }
}
