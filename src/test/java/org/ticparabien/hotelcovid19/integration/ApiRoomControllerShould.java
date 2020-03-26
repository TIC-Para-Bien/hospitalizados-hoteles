package org.ticparabien.hotelcovid19.integration;

import org.ticparabien.hotelcovid19.controller.api.RoomsController;
import org.ticparabien.hotelcovid19.domain.RoomDTO;
import org.ticparabien.hotelcovid19.domain.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = RoomsController.class, secure = false)
public class ApiRoomControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService RoomServiceStub;

    @Test
    public void retrieve_Room_parse_to_json() throws Exception {
        List<RoomDTO> RoomDTOList = asList(new RoomDTO(1, "La cuchara sana"));
        given(RoomServiceStub.retrieveRoomsFor(any())).willReturn(RoomDTOList);
        String expected = "[{\"id\":1, \"name\":\"La cuchara sana\"}]";

        mockMvc.perform(get("/api/Rooms"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}
