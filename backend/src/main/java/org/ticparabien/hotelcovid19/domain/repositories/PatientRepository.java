package org.ticparabien.hotelcovid19.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("FROM Patient p WHERE p.personalId = :personalId")
    Optional<Patient> findByPersonalId(@Param("personalId") String personalId);
}
