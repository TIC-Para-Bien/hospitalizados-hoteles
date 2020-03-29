package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;
import org.ticparabien.hotelcovid19.domain.mapper.PatientMapper;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.Optional;

@Service
public class FindPatientById {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public FindPatientById(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public Optional<PatientDto> execute(Integer id) {
        return patientRepository.findById(id)
                .map(patientMapper::mapToDto);
    }
}
