package org.ticparabien.hotelcovid19.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.LastReportedHealthRecord;
import org.ticparabien.hotelcovid19.domain.Patient;

import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<LastReportedHealthRecord, Integer>{
    List<LastReportedHealthRecord> findAll();
}
