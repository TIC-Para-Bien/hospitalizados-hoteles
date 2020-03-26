package org.ticparabien.hotelcovid19.helpers;

import org.ticparabien.hotelcovid19.domain.RoomService;
import org.ticparabien.hotelcovid19.infrastructure.PostgresRoomRepository;

public class TestFactory {

    public static RoomService RoomService(String connection) {
        return new RoomService(new PostgresRoomRepository(connection));
    }
}
