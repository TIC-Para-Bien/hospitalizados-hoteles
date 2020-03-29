package org.ticparabien.hotelcovid19.domain.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Room;
import org.ticparabien.hotelcovid19.domain.dto.RoomDto;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoomMapper {

    private PatientMapper patientMapper;

    public RoomDto mapToDto(Room entity) {
        return RoomDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .info(entity.getInfo())
                .maxCapacity(entity.getMaxCapacity())
                .patients(entity.getPatients().stream()
                    .map(patientMapper::mapToDto)
                    .collect(Collectors.toList()))
                .build();
    }

    public Room mapToEntity(RoomDto dto) {
        return Room.builder()
                .name(dto.getName())
                .info(dto.getInfo())
                .maxCapacity(dto.getMaxCapacity())
                .build();
    }
}
