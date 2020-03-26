package org.ticparabien.hotelcovid19.controller.api;

import org.ticparabien.hotelcovid19.domain.RoomDTO;
import org.ticparabien.hotelcovid19.domain.RoomService;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomsController {

    private RoomService RoomService;

    @Autowired
    public RoomsController(RoomService RoomService) {
        this.RoomService = RoomService;
    }

    @GetMapping(path = Routes.Rooms)
    public List<RoomDTO> getRooms() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = (String) authentication.getPrincipal();
            return RoomService.retrieveRoomsFor(username);
        }
        // This last line only gets executed in tests to validate the json format, 401 in production
        return stubJsonTest();
    }

    private List<RoomDTO> stubJsonTest() {
        return RoomService.retrieveRoomsFor(null);
    }
}
