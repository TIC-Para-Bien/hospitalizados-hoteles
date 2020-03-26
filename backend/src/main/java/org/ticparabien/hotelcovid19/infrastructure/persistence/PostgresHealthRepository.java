package org.ticparabien.hotelcovid19.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRepository;

@Repository
public class PostgresHealthRepository implements HealthRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(HealthRegisterDto dto) {
        jdbcTemplate.update("INSERT INTO health_records (patient_id, temperature, cough, headache, throat_ache) VALUES(?,?,?,?,?)",
                dto.patientId, dto.fever, dto.cough, dto.headache, dto.throatAche);
    }
}
