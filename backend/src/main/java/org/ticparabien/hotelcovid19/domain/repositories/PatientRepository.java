package org.ticparabien.hotelcovid19.domain.repositories;

import org.ticparabien.hotelcovid19.domain.Patient;

import java.util.List;

public interface PatientRepository {
    List<Patient> findAll();
    List<Patient> findAllWithFever();
}
