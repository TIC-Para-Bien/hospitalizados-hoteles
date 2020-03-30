package org.ticparabien.hotelcovid19.domain.actions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.dto.HealthRecordDto;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.exception.PatientNotFound;
import org.ticparabien.hotelcovid19.domain.mapper.HealthRecordMapper;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

@AllArgsConstructor
@Service
public class AddHealthRegister {

    private final HealthRecordRepository healthRecordRepository;

    private final PatientRepository patientRepository;

    private final HealthRecordMapper healthRecordMapper;

    public Integer execute(HealthRecordDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new PatientNotFound("Patient with id " + dto.getPatientId() + " not found"));

        HealthRecord entity = healthRecordMapper.mapToEntity(dto);
        entity.setPatient(patient);
        entity = healthRecordRepository.save(entity);

        return entity.getId();
    }
}
