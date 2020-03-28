package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser("patient")
public class RegisterHealthValuesShould {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void registering_high_fever_should_inform_doctors() throws Exception {
        Patient patientWithFever = addPatient();
        Float expectedTemperature = Float.valueOf(38f);
        String sentJson = healthRecordJson(patientWithFever, expectedTemperature);

        mvc.perform(post(Routes.PatientHealthRecord)
                .content(sentJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));

        mvc.perform(get(Routes.HighFeverPatients)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].patientId", is(patientWithFever.getId())))
                .andExpect(jsonPath("$.[0].temperature", is((double) expectedTemperature)));
    }

    private String healthRecordJson(Patient patientWithFever, Float temperature) throws JsonProcessingException {
        HealthRegisterDto dto = HealthRegisterDto.builder()
                .cough(true)
                .headache(true)
                .temperature(temperature)
                .throatAche(true)
                .patientId(patientWithFever.getPersonalId())
                .build();

        return objectMapper.writeValueAsString(dto);
    }

    public Patient addPatient() {
        Patient patient = new Patient();
        patient.setName("pablo");
        patient.setPersonalId("484849384");
        patient.setPhone("697839848");

        return patientRepository.saveAndFlush(patient);
    }
}


