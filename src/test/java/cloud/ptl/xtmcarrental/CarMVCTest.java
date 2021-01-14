package cloud.ptl.xtmcarrental;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarMVCTest {

    private Long clientId;
    private Long carId;

    @BeforeEach
    void post_car_and_client(@Autowired MockMvc mockMvc) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/client")
                        .param("firstName", "Aaaa")
                        .param("lastName", "Bbbb")
                        .param("password", "1234")
        ).andExpect(status().is2xxSuccessful()).andDo(print()).andReturn();

        this.clientId = IdExtractor.extract(mvcResult);

        mvcResult = mockMvc.perform(
                post("/car/")
                        .param("numberPlate", "dsfgfghdsa")
                        .param("model", "ghfder43refd")
        ).andExpect(status().is2xxSuccessful()).andReturn();

        this.carId = IdExtractor.extract(mvcResult);
    }

    @Test
    void rent_car(@Autowired MockMvc mockMvc) throws Exception {
        System.out.println(clientId.toString());
        mockMvc.perform(
                patch("/car/rent/{id}", carId)
                        .param("clientId", clientId.toString())

        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void rent_and_return(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(
                patch("/car/rent/{id}", carId)
                        .param("clientId", clientId.toString())

        ).andExpect(status().isOk()).andDo(print());

        mockMvc.perform(
                patch("/car/return/{id}", carId)
                        .param("clientId", clientId.toString())
                        .param("distanceCovered", "200")

        ).andExpect(status().is2xxSuccessful()).andDo(print());
    }

    @Test
    void rerent_car(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(
                patch("/car/rent/{id}", carId.toString())
                    .param("clientId", clientId.toString())

        ).andExpect(status().isOk()).andDo(print());

        mockMvc.perform(
                patch("/car/rent/{id}", carId.toString())
                        .param("clientId", clientId.toString())

        ).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Test
    void rereturn_car(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(
                patch("/car/rent/{id}", carId.toString())
                        .param("clientId", clientId.toString())

        ).andExpect(status().isOk()).andDo(print());

        mockMvc.perform(
                patch("/car/return/{id}", carId.toString())
                        .param("clientId", clientId.toString())
                        .param("distanceCovered", "200")

        ).andExpect(status().is2xxSuccessful()).andDo(print());

        mockMvc.perform(
                patch("/car/return/{id}", carId.toString())
                        .param("clientId", clientId.toString())
                        .param("distanceCovered", "200")

        ).andExpect(status().is4xxClientError()).andDo(print());
    }

    @Test
    void return_not_rented(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(
                patch("/car/return/{id}", carId.toString())
                        .param("clientId", clientId.toString())
                        .param("distanceCovered", "200")

        ).andExpect(status().is4xxClientError()).andDo(print());
    }

}
