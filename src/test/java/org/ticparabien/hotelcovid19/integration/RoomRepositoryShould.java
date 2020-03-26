package org.ticparabien.hotelcovid19.integration;

import org.ticparabien.hotelcovid19.Configuration;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.helpers.IntegrationTests;
import org.ticparabien.hotelcovid19.domain.Room;
import org.ticparabien.hotelcovid19.domain.RoomDTO;
import org.ticparabien.hotelcovid19.helpers.Any;
import org.ticparabien.hotelcovid19.infrastructure.PostgresRoomRepository;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RoomRepositoryShould extends IntegrationTests {
/*
    private PostgresRoomRepository RoomRepository;

    @Before
    public void given_a_repository_and_a_database() {
        RoomRepository = new PostgresRoomRepository(Configuration.connectionTestDatabase);
    }

    @Test
    public void finds_user_by_credentials() {

        String hash = "someKindOfHash124asdfavas3rasd";
        String username = "test";
        try(Connection connection = sql2o.open()) {
            connection.createQuery(
                    "INSERT INTO owners(username, passwordhash) values (:username, :passwordhash)")
                    .addParameter("username", username)
                    .addParameter("passwordhash", hash)
                    .executeUpdate();
        }

        assertThat(RoomRepository.getOwnerHashedPassword(username)).isEqualTo(hash);

    }

    @Test
    public void not_create_a_web_user_if_it_already_exists() throws SQLException {

        String username = "Parroty";
        Patient patient = new Patient(username, "Money");
        Room RoomOne = new Room(Any.string(), patient);
        Room RoomTwo = new Room(Any.string(), patient);

        RoomRepository.create(RoomOne);
        RoomRepository.create(RoomTwo);

        List<String> ownernamesInDatabase = retrieveownernames();
        assertThat(ownernamesInDatabase.size()).isEqualTo(1);
        assertThat(ownernamesInDatabase.get(0)).isEqualTo(username);

    }

    @Test
    public void find_all_Rooms_for_a_web_user() throws SQLException {
        String RoomOneName = "Parrot Bar";
        String RoomTwoName = "Sloth Bar";
        Patient patient = new Patient("Parroty", "Money");
        Room RoomOne = new Room(RoomOneName, patient);
        Room RoomTwo = new Room(RoomTwoName, patient);
        RoomRepository.create(RoomOne);
        RoomRepository.create(RoomTwo);

        List<RoomDTO> retrievedRooms = RoomRepository.retrieveRoomsFor(patient.getRoomNumber());

        assertThat(retrievedRooms.size()).isEqualTo(2);
        assertThat(retrievedRooms.get(0).name).isEqualTo(RoomOneName);
        assertThat(retrievedRooms.get(1).name).isEqualTo(RoomTwoName);
    }

    private Patient anyowner() {
        return new Patient(Any.string(), Any.string());
    }

    private List<String> retrieveownernames() {
        return null;

        try(Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT username FROM owners")
                    .executeScalarList(String.class);
        }
    }
    */
}

