package org.ticparabien.hotelcovid19.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.HealthRecord;

import java.util.Date;
import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer>{

    // TODO We might need to consider different timezones. Careful deployed server timezone -> Force timezone at application startup to avoid getting the server one.

    @Query("FROM HealthRecord hr LEFT JOIN FETCH hr.patient p WHERE hr.createdOn > :date AND hr.temperature >= :temperature GROUP BY hr.patient.id ORDER BY hr.createdOn")
    List<HealthRecord> findAllLastRecordsWithTemperatureHigherThanAfterDate(@Param("temperature") float temperature, @Param("date") Date date);
}
