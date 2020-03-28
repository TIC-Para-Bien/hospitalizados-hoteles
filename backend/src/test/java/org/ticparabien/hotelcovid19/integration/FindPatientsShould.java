package org.ticparabien.hotelcovid19.integration;

import org.junit.Test;
import org.ticparabien.hotelcovid19.domain.LastReportedHealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.actions.FindPatientsWithHighFever;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class FindPatientsShould {

    @Test
    public void filter_patients_with_high_temperature() {

        PatientRepository repository = mock(PatientRepository.class);
        List<Patient> patients = new ArrayList<>();
        Patient patientWithHighTemperature = new Patient(1, "personalid", "123123123", "nombre");
        patientWithHighTemperature.setLastReportedHealthRecord(new LastReportedHealthRecord(38));
        patients.add(patientWithHighTemperature);

        Patient patientWithNormalTemperature = new Patient(2, "personalid", "123123123", "nombre");
        patientWithNormalTemperature.setLastReportedHealthRecord(new LastReportedHealthRecord(36));
        patients.add(patientWithNormalTemperature);

        given(repository.findAll()).willReturn(patients);

        FindPatientsWithHighFever service = new FindPatientsWithHighFever(repository);

        List<Patient> patientsWithHighTemperature = service.execute();
        assertThat(patientsWithHighTemperature.size()).isEqualTo(1);
        assertThat(patientsWithHighTemperature.get(0).temperature()).isEqualTo(38);
    }
}
