package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RegisterPatientShould {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void registering_patient_should_allow_employee_to_get_password_and_hand_it_to_patient_and_to_get_patient_info() throws Exception {
        String name = "Eustaquio";
        String personalId = "personalId";
        String phone = "phone";
        PatientDto dto = PatientDto.builder()
                .name(name)
                .personalId(personalId)
                .phone(phone)
                .build();
        String body = objectMapper.writeValueAsString(dto);

        String patientResourceUrl = mvc.perform(post("/api/patients")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("$.password", notNullValue()))
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        mvc.perform(get(patientResourceUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.personalId", is(personalId)))
                .andExpect(jsonPath("$.phone", is(phone)));
    }
}


