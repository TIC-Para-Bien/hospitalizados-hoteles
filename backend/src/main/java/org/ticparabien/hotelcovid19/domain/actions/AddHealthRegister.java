package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.LastReportedHealthRecord;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;

@Service
public class AddHealthRegister {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    public AddHealthRegister(HealthRecordRepository healthRecordRepository) {
        this.healthRecordRepository = healthRecordRepository;
    }

    public void execute(LastReportedHealthRecord dto) {
        healthRecordRepository.save(dto);
    }
}
