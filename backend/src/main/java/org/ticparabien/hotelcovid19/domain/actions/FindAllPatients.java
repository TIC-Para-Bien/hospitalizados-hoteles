package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;
import org.ticparabien.hotelcovid19.domain.mapper.PatientMapper;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllPatients {

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public FindAllPatients(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientDto> execute() {
        return patientRepository.findAll().stream()
                .map(patientMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
