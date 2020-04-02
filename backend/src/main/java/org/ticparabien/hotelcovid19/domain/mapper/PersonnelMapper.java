package org.ticparabien.hotelcovid19.domain.mapper;

import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Personnel;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPersonnelRequestDto;

@Component
public class PersonnelMapper {

    public Personnel mapToEntity(RegisterPersonnelRequestDto dto) {
        return Personnel.builder()
                .personnelId(dto.getPersonnelId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
    }
}
