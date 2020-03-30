package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.dto.HealthRecordDto;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ListPatientsShould {

    private static final String PATIENTS_BASE_URI = "/api/patients";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void beforeEach() {
        healthRecordRepository.deleteAll();
        healthRecordRepository.flush();
        patientRepository.deleteAll();
        patientRepository.flush();
    }

    @Test
    void listing_all_patients_should_return_them_with_all_their_health_records_ordered_by_most_recent() throws Exception {
        Instant now = Instant.now();
        Date date1 = Date.from(now.minus(3, ChronoUnit.HOURS));
        Date date2 = Date.from(now.minus(1, ChronoUnit.HOURS));
        Date date3 = Date.from(now.minus(2, ChronoUnit.HOURS));
        createTestData(date1, date2, date3);

        String response = mvc.perform(get(PATIENTS_BASE_URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, PatientDto.class);
        List<PatientDto> patients = objectMapper.readValue(response, javaType);
        assertThat(patients, hasSize(1));
        List<HealthRecordDto> healthRecords = patients.get(0).getHealthRecords();
        assertThat(healthRecords, IsIterableContainingInOrder.contains(
                hasProperty("createdOn", is(date2)),
                hasProperty("createdOn", is(date3)),
                hasProperty("createdOn", is(date1))
        ));
    }

    @Test
    void listing_one_patient_should_return_it_with_all_their_health_records_ordered_by_most_recent() throws Exception {
        Instant now = Instant.now();
        Date date1 = Date.from(now.minus(2, ChronoUnit.HOURS));
        Date date2 = Date.from(now.minus(3, ChronoUnit.HOURS));
        Date date3 = Date.from(now.minus(1, ChronoUnit.HOURS));
        createTestData(date1, date2, date3);

        String response = mvc.perform(get(PATIENTS_BASE_URI + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PatientDto patient = objectMapper.readValue(response, PatientDto.class);
        List<HealthRecordDto> healthRecords = patient.getHealthRecords();
        assertThat(healthRecords, IsIterableContainingInOrder.contains(
                hasProperty("createdOn", is(date3)),
                hasProperty("createdOn", is(date1)),
                hasProperty("createdOn", is(date2))
        ));
    }

    @Test
    void listing_all_patients_older_than_65_should_return_them_with_all_their_health_records_ordered_by_most_recent() throws Exception {
        Instant now = Instant.now();
        Date date1 = Date.from(now.minus(2, ChronoUnit.HOURS));
        Date date2 = Date.from(now.minus(3, ChronoUnit.HOURS));
        Date date3 = Date.from(now.minus(1, ChronoUnit.HOURS));
        createTestDataToOldPeople(date1, date2, date3, 30, 78, 54, 89);

        Integer older = 65;
        String response = mvc.perform(get(PATIENTS_BASE_URI + "?older=" + older)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].age", greaterThan(older)))
                .andExpect(jsonPath("$.[0].healthRecords", hasSize(3)))
                .andExpect(jsonPath("$.[0].healthRecords[0].createdOn", is(date3)))
                .andExpect(jsonPath("$.[0].healthRecords[1].createdOn", is(date2)))
                .andExpect(jsonPath("$.[0].healthRecords[2].createdOn", is(date1)))
                .andExpect(jsonPath("$.[1].age", greaterThan(older)))
                .andExpect(jsonPath("$.[1].healthRecords", hasSize(3)))
                .andExpect(jsonPath("$.[1].healthRecords[0].createdOn", is(date3)))
                .andExpect(jsonPath("$.[1].healthRecords[1].createdOn", is(date2)))
                .andExpect(jsonPath("$.[1].healthRecords[2].createdOn", is(date1)))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private void createTestDataToOldPeople(Date date1, Date date2, Date date3,
                                           Integer age1, Integer age2, Integer age3, Integer age4) {
        Patient patient = createPatient(age1);
        createHealthRecord(patient, date1);
        createHealthRecord(patient, date2);
        createHealthRecord(patient, date3);

        patient = createPatient(age2);
        createHealthRecord(patient, date1);
        createHealthRecord(patient, date3);
        createHealthRecord(patient, date2);

        patient = createPatient(age3);
        createHealthRecord(patient, date2);
        createHealthRecord(patient, date3);
        createHealthRecord(patient, date1);

        patient = createPatient(age4);
        createHealthRecord(patient, date2);
        createHealthRecord(patient, date1);
        createHealthRecord(patient, date3);
    }

    private void createTestData(Date date1, Date date2, Date date3) {
       Patient patient = createPatient(20);
       createHealthRecord(patient, date1);
       createHealthRecord(patient, date2);
       createHealthRecord(patient, date3);
    }

    private Patient createPatient(Integer age){
        Patient patient = Patient.builder()
                .hashedPassword("hashedPassword")
                .personalId("personalId")
                .name("Herminia")
                .phone("phone")
                .age(age)
                .build();
        return patientRepository.save(patient);
    }

    private void createHealthRecord(Patient patient, Date date) {
        HealthRecord healthRecord = HealthRecord.builder()
                .cough(false)
                .headache(false)
                .temperature(36.0f)
                .throatAche(true)
                .patient(patient)
                .diarrhea(true)
                .joinPain(true)
                .musclePain(true)
                .palpitations(true)
                .phlegm(true)
                .respiratoryDistress(false)
                .smellTasteLoss(true)
                .build();
        healthRecord = healthRecordRepository.save(healthRecord);
        healthRecord.setCreatedOn(date);
        healthRecordRepository.save(healthRecord);
    }

}


