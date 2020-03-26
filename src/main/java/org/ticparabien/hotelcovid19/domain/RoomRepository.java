package org.ticparabien.hotelcovid19.domain;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public
interface RoomRepository {

    void create(Room Room) throws SQLException;

    List<RoomDTO> retrieveRoomsFor(String username);

    String getOwnerHashedPassword(String username);
}
