package org.ticparabien.hotelcovid19.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.actions.FindPatientsWithHighFever;
import org.ticparabien.hotelcovid19.domain.dto.PatientRequestByTemperatureAndDateDto;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FindPatientsWithHighFeverTest {

    @Mock
    private HealthRecordRepository healthRecordRepository;

    private FindPatientsWithHighFever action;

    @BeforeEach
    void beforeEach() {
        action = new FindPatientsWithHighFever(healthRecordRepository);
    }

    @Test
    void filter_patients_with_high_temperature() {
        float temperature = 37.5f;
        Date date = new Date();
        HealthRecord healthRecord1 = HealthRecord.builder()
                .id(1)
                .temperature(38f)
                .patient(createPatientWithFever())
                .build();
        List<HealthRecord> healthRecords = Collections.singletonList(healthRecord1);
        given(healthRecordRepository.findAllLastRecordsWithTemperatureHigherThanAfterDate(temperature, date)).willReturn(healthRecords);

        PatientRequestByTemperatureAndDateDto dto = new PatientRequestByTemperatureAndDateDto(temperature, date);
        List<HealthRecord> healthRecordList = action.execute(dto);

        assertThat(healthRecordList.size()).isEqualTo(1);
        assertThat(healthRecordList.get(0).getTemperature()).isEqualTo(38);
    }

    private Patient createPatientWithFever() {
        Patient patientWithHighTemperature = new Patient(1, "personalid", "123123123", "nombre");
        return patientWithHighTemperature;
    }
}
