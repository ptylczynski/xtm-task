package cloud.ptl.xtmcarrental;

import cloud.ptl.xtmcarrental.dao.ClientDAO;
import cloud.ptl.xtmcarrental.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Long clientId;

    @BeforeEach
    void save_client(){
        this.clientId = this.clientRepository.save(
                ClientDAO.builder()
                        .firstName("ABCD")
                        .lastName("EFGH")
                        .password("jkrhnjer")
                        .build()
        ).getId();
    }

    @Test
    void save_second_client(){
        ClientDAO saved = this.clientRepository.save(
                ClientDAO.builder()
                    .firstName("ABCD")
                    .lastName("EFGH")
                    .password("jkrhnjer")
                    .build()
        );

        assertEquals(saved.getFirstName(), "ABCD");
        assertEquals(saved.getLastName(), "EFGH");
        assertEquals(saved.getPassword(), "jkrhnjer");
        System.out.println(saved.toString());
    }

    @Test
    void read_on_client(){
        Optional<ClientDAO> client = this.clientRepository.findById(this.clientId);
        assertTrue(client.isPresent());
        assertEquals(client.get().getFirstName(), "ABCD");
        assertEquals(client.get().getLastName(), "EFGH");
        assertEquals(client.get().getPassword(), "jkrhnjer");
    }

}
