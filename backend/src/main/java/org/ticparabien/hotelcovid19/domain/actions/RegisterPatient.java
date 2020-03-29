package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientRequestDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientResponseDto;
import org.ticparabien.hotelcovid19.domain.mapper.PatientMapper;
import org.ticparabien.hotelcovid19.domain.password.PasswordGenerator;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

@Service
public class RegisterPatient {

    private static final int DEFAULT_PASSWORD_LENGTH = 8;

    private final PasswordEncoder passwordEncoder;

    private final PasswordGenerator passwordGenerator;

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public RegisterPatient(PasswordEncoder passwordEncoder, PasswordGenerator passwordGenerator, PatientRepository patientRepository,
                           PatientMapper patientMapper) {
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public RegisterPatientResponseDto execute(RegisterPatientRequestDto dto) {
        Patient patient = patientMapper.mapToEntity(dto);
        // TODO Make length configurable through the DTO at request level or by application property
        String generatedPassword = passwordGenerator.generate(DEFAULT_PASSWORD_LENGTH);
        String hashedPassword = passwordEncoder.encode(generatedPassword);
        patient.setHashedPassword(hashedPassword);

        patient = patientRepository.save(patient);

        return new RegisterPatientResponseDto(patient.getId(), generatedPassword);
    }
}
