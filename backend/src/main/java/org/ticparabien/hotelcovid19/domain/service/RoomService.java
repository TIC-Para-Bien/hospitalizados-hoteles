package org.ticparabien.hotelcovid19.domain.service;

import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.Room;
import org.ticparabien.hotelcovid19.domain.dto.RoomDto;
import org.ticparabien.hotelcovid19.domain.mapper.RoomMapper;
import org.ticparabien.hotelcovid19.domain.repositories.RoomRepository;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public Integer createRoom(RoomDto dto) {
        Room room = roomMapper.mapToEntity(dto);
        room = roomRepository.save(room);
        return room.getId();
    }

    public Optional<RoomDto> getRoomById(Integer id) {
        return roomRepository.findById(id)
                .map(roomMapper::mapToDto);
    }

    public Optional<RoomDto> getRoomByName(String name) {
        return roomRepository.findByName(name)
                .map(roomMapper::mapToDto);
    }

    public void updateRoom(Integer id, RoomDto dto) {
        Room room = roomMapper.mapToEntity(dto);
        roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }
}
