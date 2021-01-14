package cloud.ptl.xtmcarrental;

import cloud.ptl.xtmcarrental.services.CarService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClientMVCTest {

    private Long clientId;

    @BeforeEach
    void post_client(@Autowired MockMvc mockMvc) throws Exception {
        MvcResult result = mockMvc.perform(
                post("/client")
                        .param("firstName", "Aaaa")
                        .param("lastName", "Bbbb")
                        .param("password", "1234")
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();
        this.clientId = IdExtractor.extract(result);
    }

    @Test
    @Rollback
    void putAndReadClient(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(
                get("/client/{id}", clientId)
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}
