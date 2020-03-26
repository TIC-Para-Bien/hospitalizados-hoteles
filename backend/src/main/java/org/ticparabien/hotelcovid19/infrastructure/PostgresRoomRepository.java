package org.ticparabien.hotelcovid19.infrastructure;

import org.ticparabien.hotelcovid19.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;


public class PostgresRoomRepository implements RoomRepository {

    public PostgresRoomRepository(String connectionUrl) {

    }

    @Override
    public void create(Room Room) throws SQLException {
    }

    @Override
    public List<RoomDTO> retrieveRoomsFor(String username) {
        return new ArrayList<RoomDTO>();
    }

    @Override
    public String getOwnerHashedPassword(String username) {
        return hashedPasswordFor(username, "owners");
    }

    private String hashedPasswordFor(String username, final String usersTable) {
        return "Not implemented";
    }
}

