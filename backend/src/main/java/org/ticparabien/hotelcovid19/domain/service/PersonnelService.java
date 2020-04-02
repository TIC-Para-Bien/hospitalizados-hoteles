package org.ticparabien.hotelcovid19.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.Credential;
import org.ticparabien.hotelcovid19.domain.Personnel;
import org.ticparabien.hotelcovid19.domain.Role;
import org.ticparabien.hotelcovid19.domain.Roles;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPersonnelRequestDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPersonnelResponseDto;
import org.ticparabien.hotelcovid19.domain.exception.RoleNotFound;
import org.ticparabien.hotelcovid19.domain.mapper.PersonnelMapper;
import org.ticparabien.hotelcovid19.domain.password.PasswordGenerator;
import org.ticparabien.hotelcovid19.domain.repositories.PersonnelRepository;
import org.ticparabien.hotelcovid19.domain.repositories.RoleRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    @Value("${security.password.default-length}")
    private int defaultPasswordLength;

    private final PasswordEncoder passwordEncoder;

    private final PasswordGenerator passwordGenerator;

    private final RoleRepository roleRepository;

    private final PersonnelRepository personnelRepository;

    private final PersonnelMapper personnelMapper;

    public RegisterPersonnelResponseDto createHealthPersonnel(RegisterPersonnelRequestDto dto) {
        Role role = roleRepository.findByName(Roles.PERSONNEL.toString())
                .orElseThrow(() -> new RoleNotFound("Role PERSONNEL not found."));
        String generatedPassword = passwordGenerator.generate(defaultPasswordLength);
        String hashedPassword = passwordEncoder.encode(generatedPassword);
        Credential credential = Credential.builder()
                .username(dto.getPhone())
                .hashedPassword(hashedPassword)
                .roles(Collections.singleton(role))
                .build();
        Personnel personnel = personnelMapper.mapToEntity(dto);
        personnel.setCredential(credential);
        personnel = personnelRepository.save(personnel);

        return new RegisterPersonnelResponseDto(personnel.getId(), generatedPassword);
    }
}
