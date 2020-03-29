package org.ticparabien.hotelcovid19.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.ticparabien.hotelcovid19.domain.Room;
import org.ticparabien.hotelcovid19.domain.repositories.RoomRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class RoomRepositoryIT {

    private static final String NAME = "name";

    @Autowired
    private RoomRepository repository;

    @Test
    void fails_to_insert_room_with_same_name() {
        Room room = Room.builder()
                .name(NAME)
                .maxCapacity(1)
                .build();
        repository.saveAndFlush(room);
        Room newRoom = Room.builder()
                .name(NAME)
                .maxCapacity(1)
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> repository.saveAndFlush(newRoom));
    }
}
