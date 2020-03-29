package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.exception.PatientNotFound;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

@Service
public class AddHealthRegister {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    public AddHealthRegister(HealthRecordRepository healthRecordRepository, PatientRepository patientRepository) {
        this.healthRecordRepository = healthRecordRepository;
        this.patientRepository = patientRepository;
    }

    public Integer execute(HealthRegisterDto dto) {
        Patient patient = patientRepository.findByPersonalId(dto.getPatientId())
                .orElseThrow(() -> new PatientNotFound("Patient with id " + dto.getPatientId() + " not found"));

        HealthRecord entity = HealthRecord.builder()
                .cough(dto.getCough())
                .headache(dto.getHeadache())
                .temperature(dto.getTemperature())
                .throatAche(dto.getThroatAche())
                .patient(patient)
                .build();

        entity = healthRecordRepository.save(entity);

        return entity.getId();
    }
}
