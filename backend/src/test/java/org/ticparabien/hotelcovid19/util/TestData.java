package org.ticparabien.hotelcovid19.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.ticparabien.hotelcovid19.domain.Credential;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.dto.HealthRecordDto;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PersonnelRepository;

@TestComponent
public class TestData {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    public Patient createPatient(int uniqueAttribute) {
        Credential credential = Credential.builder()
                .username("user" + uniqueAttribute)
                .hashedPassword("hashedPassword")
                .build();
        Patient patient = Patient.builder()
                .name("pablo")
                .personalId("personalId" + uniqueAttribute)
                .phone("phone" + uniqueAttribute)
                .age(20)
                .credential(credential)
                .build();
        return patientRepository.saveAndFlush(patient);
    }

    public String healthRecordDtoAsJson(Patient patient) throws JsonProcessingException {
        return healthRecordDtoAsJson(patient, 36f);
    }

    public String healthRecordDtoAsJson(Patient patient, Float temperature) throws JsonProcessingException {
        HealthRecordDto dto = HealthRecordDto.builder()
                .cough(true)
                .headache(true)
                .temperature(temperature)
                .throatAche(true)
                .patientId(patient.getId())
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
}
