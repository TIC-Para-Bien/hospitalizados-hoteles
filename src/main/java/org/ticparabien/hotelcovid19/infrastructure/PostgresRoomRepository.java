package org.ticparabien.hotelcovid19.infrastructure;

import org.ticparabien.hotelcovid19.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class PostgresRoomRepository implements RoomRepository {

    private Sql2o sql2o;

    public PostgresRoomRepository(String connectionUrl) {
        sql2o = new Sql2o(connectionUrl, "hotelcovid19", "12345");
    }

    @Override
    public void create(Room Room) throws SQLException {
        System.out.println("--- Creating Room");
        String query = "INSERT INTO Rooms(id, name) VALUES (DEFAULT, :name)";

        Connection connection = sql2o.open();
        connection.getJdbcConnection().setAutoCommit(false);
        Integer RoomId = (Integer) connection.createQuery(query, true)
                .addParameter("name", Room.name())
                .executeUpdate()
                .getKey();
        createAndRelateMany(Room.owners(), RoomId, connection);
        connection.commit();
        System.out.println("--- Room created:" + RoomId);
    }

    private void createAndRelateMany(Set<Patient> owners, Integer RoomId, Connection connection) throws SQLException {
        String query = "INSERT INTO owners(username, passwordhash) " +
                       "VALUES (:username, :passwordhash)";

        for (Patient owner : owners) {
            if (ownerDoesNotExist(owner.getRoomNumber(), connection)) {
                Integer ownerId = (Integer) connection.createQuery(query, true)
                        .addParameter("username", owner.getRoomNumber())
                        .addParameter("passwordhash", owner.getPhoneNumber())
                        .executeUpdate()
                        .getKey();
                createManyToManyRelation(ownerId, RoomId);
            } else {
                createManyToManyRelation(retrieveIdFor(owner.getRoomNumber()), RoomId);
            }
        }
    }

    private void createManyToManyRelation(Integer ownerId, Integer RoomId) throws SQLException {
        String query = "INSERT INTO Rooms_owners(Room, owner) " +
                       "VALUES (:RoomId, :ownerId)";

        Connection connection = sql2o.open();
        connection.getJdbcConnection().setAutoCommit(false);
        connection.createQuery(query)
                .addParameter("ownerId", ownerId)
                .addParameter("RoomId", RoomId)
                .executeUpdate();
        connection.commit();
    }

    private boolean ownerDoesNotExist(String username, Connection connection) {
        return connection.createQuery("SELECT username FROM owners WHERE username = :username")
                .addParameter("username", username)
                .executeScalarList(String.class).size() == 0;
    }

    @Override
    public List<RoomDTO> retrieveRoomsFor(String username) {
        Integer ownerId = retrieveIdFor(username);
        String query = "SELECT Rooms.id, Rooms.name " +
                       "FROM Rooms " +
                       "LEFT JOIN Rooms_owners ON Rooms.id = Rooms_owners.Room " +
                       "WHERE Rooms_owners.owner = :ownerId";

        List<Map<String, Object>> queryResults;
        Connection connection = sql2o.open();
        queryResults = connection.createQuery(query)
                .addParameter("ownerId", ownerId)
                .executeAndFetchTable()
                .asList();

        return queryResults.stream()
                .map(this::toRoomDTO)
                .collect(toList());
    }

    private RoomDTO toRoomDTO(Map<String, Object> result) {
        return new RoomDTO((Integer) result.get("id"), (String) result.get("name"));
    }

    private Integer retrieveIdFor(String username) {
        Connection connection = sql2o.open();
        return connection.createQuery("SELECT id FROM owners WHERE username = :username")
                .addParameter("username", username)
                .executeScalarList(Integer.class).get(0);
    }

    @Override
    public String getOwnerHashedPassword(String username) {
        return hashedPasswordFor(username, "owners");
    }

    private String hashedPasswordFor(String username, final String usersTable) {
        String query = "SELECT passwordhash " +
                "FROM " + usersTable + " " +
                "WHERE username = :username";

        List<String> matchingHashedPasswords;
        Connection connection = sql2o.open();
        matchingHashedPasswords = connection.createQuery(query)
                .addParameter("username", username)
                .executeScalarList(String.class);

        if (matchingHashedPasswords.size() != 1){
            throw new UsernameNotFoundException(username);
        }

        return matchingHashedPasswords.get(0);
    }
}
