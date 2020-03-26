package org.ticparabien.hotelcovid19.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.List;

@Repository
public class PostgresPatientRepository implements PatientRepository {

    final
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresPatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Patient> findAll() {
        return jdbcTemplate.query("SELECT * FROM patient;", patientRowMapper());
    }

    @Override
    public List<Patient> findAllWithFever() {
        String querySentence = "SELECT patient.id, patient.personal_id, patient.phone, patient.name FROM patient INNER JOIN health_records hr on patient.id = hr.patient_id WHERE fever > 37.7";
        return jdbcTemplate.query(querySentence, patientRowMapper());
    }

    private RowMapper<Patient> patientRowMapper() {
        return (resultSet, i) -> new Patient(
                resultSet.getInt("id"),
                resultSet.getString("personal_id"),
                resultSet.getString("phone"),
                resultSet.getString("name")
        );
    }
}
