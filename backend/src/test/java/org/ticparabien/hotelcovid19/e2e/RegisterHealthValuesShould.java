package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;
import org.ticparabien.hotelcovid19.domain.LastReportedHealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser("patient")
public class RegisterHealthValuesShould {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

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
                .andReturn();

        MvcResult result = mvc.perform(get(Routes.HighFeverPatients)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resultContentAsString = result.getResponse().getContentAsString();

        assertThat(resultContentAsString).contains("\"patientId\":" + patientWithFever.getId());
        assertThat(resultContentAsString).contains("\"temperature\":" + expectedTemperature);
    }

    private String healthRecordJson(Patient patientWithFever, Float fever) throws JsonProcessingException {
        LastReportedHealthRecord registerForPatientWithFever = new LastReportedHealthRecord();
        registerForPatientWithFever.setCough(true);
        registerForPatientWithFever.setHeadache(true);
        registerForPatientWithFever.setThroatAche(true);
        registerForPatientWithFever.setTemperature(fever);
        registerForPatientWithFever.setPatient(patientWithFever);
        registerForPatientWithFever.setCreationOn(new Date());
        return new ObjectMapper().writeValueAsString(registerForPatientWithFever);
    }

    public Patient addPatient() {
        /*Patient patient = Patient.builder()
                .name("pablo")
                .personalId("484849384")
                .phone("697839848").build();*/

        Patient patient = new Patient();

        patient.setName("pablo");
        patient.setPersonalId("484849384");
        patient.setPhone("697839848");


        return patientRepository.saveAndFlush(patient);

    }

    public void deleteAllPatients() {
        jdbcTemplate.update("DELETE FROM patient");
    }

    private RowMapper<Patient> patientRowMapper() {
        return (resultSet, i) -> new Patient(
                resultSet.getInt("id"),
                resultSet.getString("personal_id"),
                resultSet.getString("phone"),
                resultSet.getString("name")
        );
    }
}


