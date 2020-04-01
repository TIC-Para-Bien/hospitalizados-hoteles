package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.dto.HealthRecordDto;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser("patient")
class RegisterHealthValuesShould {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    void beforeEach() {
        patientRepository.deleteAll();
    }

    @Test
    void registering_high_fever_should_inform_doctors() throws Exception {
        Patient patientWithFever = addPatient(1);
        Float expectedTemperature = 38f;
        String sentJson = healthRecordJson(patientWithFever, expectedTemperature);

        mvc.perform(post(Routes.PatientHealthRecord)
                .content(sentJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));

        Date date = Date.from(Instant.now().minus(1, ChronoUnit.HOURS));
        String requestParams = String.format("?temperature=%s&date=%s", 37.7f, sdf.format(date));
        mvc.perform(get(Routes.HighFeverPatients + requestParams)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.[0].patientId", is(patientWithFever.getId())))
                .andExpect(jsonPath("$.[0].temperature", is((double) expectedTemperature)));
    }

    @Test
    void registering_health_record_and_retrieving_it_should_return_all_symptoms() throws Exception {
        Patient patientWithFever = addPatient(1);
        Float expectedTemperature = 38f;
        String sentJson = healthRecordJson(patientWithFever, expectedTemperature);

        mvc.perform(post(Routes.PatientHealthRecord)
                .content(sentJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andReturn().getResponse().getHeader(HttpHeaders.LOCATION);

        mvc.perform(get("/api/patients/" + patientWithFever.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.healthRecords[0].temperature", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].cough", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].headache", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].throatAche", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].diarrhea", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].joinPain", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].musclePain", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].palpitations", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].phlegm", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].respiratoryDistress", notNullValue()))
                .andExpect(jsonPath("$.healthRecords[0].smellTasteLoss", notNullValue()));
    }

    private String healthRecordJson(Patient patientWithFever, Float temperature) throws JsonProcessingException {
        HealthRecordDto dto = HealthRecordDto.builder()
                .cough(true)
                .headache(true)
                .temperature(temperature)
                .throatAche(true)
                .patientId(patientWithFever.getId())
                .diarrhea(true)
                .joinPain(true)
                .musclePain(true)
                .palpitations(true)
                .phlegm(true)
                .respiratoryDistress(false)
                .smellTasteLoss(true)
                .build();

        return objectMapper.writeValueAsString(dto);
    }

    private Patient addPatient(int diff) {
        Patient patient = Patient.builder()
                .username("user" + diff)
                .hashedPassword("hashedPassword")
                .name("pablo")
                .personalId("personalId" + diff)
                .phone("phone" + diff)
                .age(20)
                .build();

        return patientRepository.saveAndFlush(patient);
    }
}


