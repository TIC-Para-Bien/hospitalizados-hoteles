package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.dto.PatientRequestByTemperatureAndDateDto;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;

import java.util.List;

@Service
public class FindPatientsWithHighFever {

    private final HealthRecordRepository healthRecordRepository;

    @Autowired
    public FindPatientsWithHighFever(HealthRecordRepository healthRecordRepository) {
        this.healthRecordRepository = healthRecordRepository;
    }

    public List<HealthRecord> execute(PatientRequestByTemperatureAndDateDto dto) {
        return healthRecordRepository.findAllLastRecordsWithTemperatureHigherThanAfterDate(dto.getTemperature(), dto.getDate());
    }
}
