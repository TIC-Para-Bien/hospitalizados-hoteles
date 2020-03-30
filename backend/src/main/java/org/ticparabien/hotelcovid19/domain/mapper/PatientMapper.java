package org.ticparabien.hotelcovid19.domain.mapper;

import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientRequestDto;

@Component
public class PatientMapper {

    public Patient mapToEntity(RegisterPatientRequestDto dto) {
        return Patient.builder()
                .personalId(dto.getPersonalId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }

    public PatientDto mapToDto(Patient entity) {
        return PatientDto.builder()
                .id(entity.getId())
                .personalId(entity.getPersonalId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .build();
    }
}
