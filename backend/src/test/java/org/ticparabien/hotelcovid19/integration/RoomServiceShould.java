package org.ticparabien.hotelcovid19.integration;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class RoomServiceShould {

    @Disabled
    @Test
    void create_an_organization_with_one_owner_allowed() throws SQLException {
        /*
        RoomService RoomService = TestFactory.RoomService(Configuration.connectionTestDatabase);
        Credentials webCredentials = new Credentials("web_username", "web_password");
        String ftpHomeFolder = "ftp_home_folder";
        RoomService.create("Parrot Bar", webCredentials);

        boolean isOwnerAllowed = RoomService.isOwnerAllowed(webCredentials.username, webCredentials.password);

        assertThat(isOwnerAllowed).isTrue();
         */
    }

    @Disabled
    @Test
    void retrieve_all_Rooms_for_an_owner_given_its_username_and_password() throws SQLException {
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
