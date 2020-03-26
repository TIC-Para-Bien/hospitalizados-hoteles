package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.ticparabien.hotelcovid19.domain.Patient;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void registering_high_fever_should_inform_doctors() throws Exception {
        HealthRegisterDto registerForPatientWithFever = new HealthRegisterDto();
        Patient patientWithFever = add_patient();
        registerForPatientWithFever.patientId = patientWithFever.id;
        registerForPatientWithFever.fever = BigDecimal.valueOf(39);
        ObjectMapper mapper = new ObjectMapper();
        String sentJson = mapper.writeValueAsString(registerForPatientWithFever);

        mvc.perform(post(Routes.PatientHealthRecord)
                .content(sentJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        HealthRegisterDto registerForPatientWithNoFever = new HealthRegisterDto();
        Patient patientWithNoFever = add_patient();
        registerForPatientWithNoFever.patientId = patientWithNoFever.id;
        registerForPatientWithNoFever.fever = BigDecimal.valueOf(36);
        sentJson = mapper.writeValueAsString(registerForPatientWithNoFever);

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

        assertThat(resultContentAsString).contains("\"id\":" + patientWithFever.id);
        assertThat(resultContentAsString).doesNotContain("\"id\":" + patientWithNoFever.id);
    }

    public Patient add_patient() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO patient(personal_id, name, phone) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "12345678A");
            ps.setString(2, "Irrelevant name");
            ps.setString(3, "650123456");
            return ps;
        }, keyHolder);
        Number insertedPatientId = (Number) keyHolder.getKeyList().get(0).get("id");

        return jdbcTemplate.queryForObject("SELECT * FROM patient WHERE id = ?", new Object[]{insertedPatientId}, patientRowMapper());
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


