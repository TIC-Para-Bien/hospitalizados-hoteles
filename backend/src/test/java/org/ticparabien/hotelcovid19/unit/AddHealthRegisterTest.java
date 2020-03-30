package org.ticparabien.hotelcovid19.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ticparabien.hotelcovid19.domain.dto.HealthRecordDto;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.actions.AddHealthRegister;
import org.ticparabien.hotelcovid19.domain.exception.PatientNotFound;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddHealthRegisterTest {

    @Mock
    private HealthRecordRepository healthRecordRepository;

    @Mock
    private PatientRepository patientRepository;

    private AddHealthRegister action;

    @BeforeEach
    void beforeEach() {
        action = new AddHealthRegister(healthRecordRepository, patientRepository);
    }

    @Test
    void given_AHealthRecordBelongingToAnExistingPatient_When_SavingTheRecord_Then_RecordIsSaved() {
        int patientId = 1;
        String personalId = "1321313";
        Patient patient = Patient.builder()
                .id(patientId)
                .personalId(personalId)
                .build();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        HealthRecord healthRecord = HealthRecord.builder()
                .id(1)
                .throatAche(true)
                .build();
        when(healthRecordRepository.save(any())).thenReturn(healthRecord);
        HealthRecordDto dto = HealthRecordDto.builder()
                .patientId(patientId)
                .build();

        action.execute(dto);

        verify(healthRecordRepository, times(1)).save(any());
    }

    @Test
    void given_AHealthRecordBelongingToANonExistentPatient_When_SavingTheRecord_Then_ThrowsPatientNotFound() {
        Integer patientId = 1;
        HealthRecordDto dto = HealthRecordDto.builder()
                .patientId(patientId)
                .build();
        when(patientRepository.findById(patientId)).thenThrow(PatientNotFound.class);

        assertThrows(PatientNotFound.class, () -> action.execute(dto));
    }
}