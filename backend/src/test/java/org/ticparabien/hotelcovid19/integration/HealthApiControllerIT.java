package org.ticparabien.hotelcovid19.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.Roles;
import org.ticparabien.hotelcovid19.util.TestData;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.ticparabien.hotelcovid19.util.TestUtils.anonymousPostRequest;
import static org.ticparabien.hotelcovid19.util.TestUtils.authenticatedPostRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Import(TestData.class)
class HealthApiControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestData testData;

    @Test
    void only_authenticated_should_be_able_register_a_health_record() throws Exception {
        Patient patient = testData.createPatient(1);
        String body = testData.healthRecordDtoAsJson(patient);

        // Registered patient
        authenticatedPostRequest(mvc, Routes.PatientHealthRecord, Roles.PATIENT, body, status().isCreated(), header().exists(HttpHeaders.LOCATION));

        // Registered personnel
        authenticatedPostRequest(mvc, Routes.PatientHealthRecord, Roles.PERSONNEL, body, status().isCreated(), header().exists(HttpHeaders.LOCATION));

        // Registered admin
        authenticatedPostRequest(mvc, Routes.PatientHealthRecord, Roles.ADMIN, body, status().isCreated(), header().exists(HttpHeaders.LOCATION));

        // Not registered
        anonymousPostRequest(mvc, Routes.PatientHealthRecord, body, status().isFound(), header().string(HttpHeaders.LOCATION, "http://localhost/login"));
    }
}


