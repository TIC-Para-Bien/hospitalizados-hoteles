package org.ticparabien.hotelcovid19.domain.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientRequestDto;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PatientMapper {

    HealthRecordMapper healthRecordMapper;

    public Patient mapToEntity(RegisterPatientRequestDto dto) {
        return Patient.builder()
                .username(dto.getPhone())
                .personalId(dto.getPersonalId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .age(dto.getAge())
                .build();
    }

    public PatientDto mapToDto(Patient entity) {
        return PatientDto.builder()
                .id(entity.getId())
                .personalId(entity.getPersonalId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .age(entity.getAge())
                .healthRecords(entity.getHealthRecords().stream()
                        .map(healthRecordMapper::mapToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
