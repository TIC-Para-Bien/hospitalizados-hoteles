package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.exception.PatientNotFound;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

@Component
public class CheckOutPatient {

    private final PatientRepository patientRepository;

    public CheckOutPatient(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void execute(Integer patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFound("Patient with ID " + patientId + " not found."));
        patient.setRoom(null);
        patientRepository.save(patient);
    }
}
