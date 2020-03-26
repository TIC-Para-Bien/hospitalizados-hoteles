package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser("patient")
public class RegisterHealthValuesShould {
    //@Autowired
    //private RegisterHealth registerHealth;
    @Autowired
    private MockMvc mvc;

    @Test
    public void registering_high_fever_should_inform_doctors() throws Exception {
        HealthRegisterDto register = new HealthRegisterDto();
        register.patientId = "1";
        register.fever = BigDecimal.valueOf(39);
        ObjectMapper mapper = new ObjectMapper();
        String sentJson = mapper.writeValueAsString(register);

        mvc.perform(post("/register-health")
                        .content(sentJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult result = mvc.perform(get("/high-fever-patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultContentAsString = result.getResponse().getContentAsString();

        assertThat(resultContentAsString).contains("\"patientId\":\"1\"");
    }
}


