package org.ticparabien.hotelcovid19.domain;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository RoomRepository;

    @Autowired
    public RoomService(RoomRepository RoomRepository) {
        this.RoomRepository = RoomRepository;
    }

    public void create(String name, Credentials webCredentials) throws SQLException {
        String hashedWebPassword = BCrypt.hashpw(webCredentials.password, BCrypt.gensalt(12));
        Patient patient = new Patient(webCredentials.username, hashedWebPassword);
        Room Room = new Room(name, patient);
        RoomRepository.create(Room);
    }

    public List<RoomDTO> retrieveRoomsFor(String username) {
        return RoomRepository.retrieveRoomsFor(username);
    }

    public boolean isOwnerAllowed(String username, String password) {
        String retrievedPasswordHash = RoomRepository.getOwnerHashedPassword(username);
        return BCrypt.checkpw(password, retrievedPasswordHash);
    }
}
