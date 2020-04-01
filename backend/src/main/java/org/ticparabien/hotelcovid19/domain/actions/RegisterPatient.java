package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticparabien.hotelcovid19.domain.Credential;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.Role;
import org.ticparabien.hotelcovid19.domain.Roles;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientRequestDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientResponseDto;
import org.ticparabien.hotelcovid19.domain.exception.RoleNotFound;
import org.ticparabien.hotelcovid19.domain.mapper.PatientMapper;
import org.ticparabien.hotelcovid19.domain.password.PasswordGenerator;
import org.ticparabien.hotelcovid19.domain.repositories.CredentialRepository;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;
import org.ticparabien.hotelcovid19.domain.repositories.RoleRepository;

import java.util.Collections;

@Service
@Transactional
public class RegisterPatient {

    private static final int DEFAULT_PASSWORD_LENGTH = 8;

    private final PasswordEncoder passwordEncoder;

    private final PasswordGenerator passwordGenerator;

    private final CredentialRepository credentialRepository;

    private final RoleRepository roleRepository;

    private final PatientRepository patientRepository;

    private final PatientMapper patientMapper;

    public RegisterPatient(PasswordEncoder passwordEncoder, PasswordGenerator passwordGenerator, CredentialRepository credentialRepository,
                           RoleRepository roleRepository, PatientRepository patientRepository, PatientMapper patientMapper) {
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.credentialRepository = credentialRepository;
        this.roleRepository = roleRepository;
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public RegisterPatientResponseDto execute(RegisterPatientRequestDto dto) {
        // TODO Make length configurable through the DTO at request level or by application property
        Role role = roleRepository.findByName(Roles.PATIENT.toString())
                .orElseThrow(() -> new RoleNotFound("Role PATIENT not found."));
        String generatedPassword = passwordGenerator.generate(DEFAULT_PASSWORD_LENGTH);
        String hashedPassword = passwordEncoder.encode(generatedPassword);
        Credential credential = Credential.builder()
                .username(dto.getPhone())
                .hashedPassword(hashedPassword)
                .roles(Collections.singleton(role))
                .build();
        credentialRepository.save(credential);

        Patient patient = patientMapper.mapToEntity(dto);
        patient.setCredential(credential);
        patient = patientRepository.save(patient);

        return new RegisterPatientResponseDto(patient.getId(), generatedPassword);
    }
}
