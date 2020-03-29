package org.ticparabien.hotelcovid19.domain.mapper;

import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Room;
import org.ticparabien.hotelcovid19.domain.dto.RoomDto;

@Component
public class RoomMapper {

    public RoomDto mapToDto(Room entity) {
        return RoomDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .info(entity.getInfo())
                .maxCapacity(entity.getMaxCapacity())
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
