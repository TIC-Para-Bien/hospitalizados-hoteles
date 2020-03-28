package org.ticparabien.hotelcovid19.unit;

import org.junit.jupiter.api.Test;
import org.ticparabien.hotelcovid19.domain.LastReportedHealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.actions.FindPatientsWithHighFever;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class FindPatientsWithHighFeverTest {

    @Test
    void filter_patients_with_high_temperature() {
        PatientRepository repository = mock(PatientRepository.class);
        List<Patient> patients = Arrays.asList(createPatientWithFever(), createPatientWithoutFever());
        given(repository.findAll()).willReturn(patients);

        FindPatientsWithHighFever service = new FindPatientsWithHighFever(repository);
        List<Patient> patientsWithHighTemperature = service.execute();

        assertThat(patientsWithHighTemperature.size()).isEqualTo(1);
        assertThat(patientsWithHighTemperature.get(0).temperature()).isEqualTo(38);
    }

    private Patient createPatientWithFever() {
        LastReportedHealthRecord healthRecord = LastReportedHealthRecord.builder().temperature(38f).build();
        Patient patientWithHighTemperature = new Patient(1, "personalid", "123123123", "nombre");
        patientWithHighTemperature.setLastReportedHealthRecord(healthRecord);
        return patientWithHighTemperature;
    }

    private Patient createPatientWithoutFever() {
        LastReportedHealthRecord healthRecord = LastReportedHealthRecord.builder().temperature(36.5f).build();
        Patient patientWithoutHighTemperature = new Patient(2, "personalid", "123123123", "nombre");
        patientWithoutHighTemperature.setLastReportedHealthRecord(healthRecord);
        return patientWithoutHighTemperature;
    }
}
