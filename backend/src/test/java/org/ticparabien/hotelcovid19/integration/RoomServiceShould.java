package org.ticparabien.hotelcovid19.integration;

import org.ticparabien.hotelcovid19.Configuration;
import org.ticparabien.hotelcovid19.helpers.IntegrationTests;
import org.ticparabien.hotelcovid19.domain.Credentials;
import org.ticparabien.hotelcovid19.domain.RoomDTO;
import org.ticparabien.hotelcovid19.domain.RoomService;
import org.ticparabien.hotelcovid19.helpers.TestFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RoomServiceShould extends IntegrationTests {

    @Test
    public void create_an_organization_with_one_owner_allowed() throws SQLException {
        /*
        RoomService RoomService = TestFactory.RoomService(Configuration.connectionTestDatabase);
        Credentials webCredentials = new Credentials("web_username", "web_password");
        String ftpHomeFolder = "ftp_home_folder";
        RoomService.create("Parrot Bar", webCredentials);

        boolean isOwnerAllowed = RoomService.isOwnerAllowed(webCredentials.username, webCredentials.password);

        assertThat(isOwnerAllowed).isTrue();
         */
    }

    @Test
    public void retrieve_all_Rooms_for_an_owner_given_its_username_and_password() throws SQLException {
        /*
        RoomService RoomService = TestFactory.RoomService(Configuration.connectionTestDatabase);
        String firstRoomName = "First Room";
        String secondRoomName = "Second Room";
        Credentials webCredentials = new Credentials("web_username", "web_password");
        RoomService.create(firstRoomName, webCredentials);
        RoomService.create(secondRoomName, webCredentials);

        List<RoomDTO> Rooms = RoomService.retrieveRoomsFor(webCredentials.username);

        assertThat(Rooms.size()).isEqualTo(2);
        assertThat(Rooms.get(0).name).isEqualTo(firstRoomName);
        assertThat(Rooms.get(1).name).isEqualTo(secondRoomName);

         */
    }
}
