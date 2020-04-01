package org.ticparabien.hotelcovid19.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ticparabien.hotelcovid19.domain.Credential;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.dto.RoomDto;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RegisterRoomsShould {

    private static final String PATIENTS_BASE_URI = "/api/patients";

    private static final String ROOMS_BASE_URI = "/api/rooms";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void beforeEach() {
        patientRepository.deleteAll();
    }

    @Test
    void creating_rooms_should_allow_checking_in_and_out_patients_in_rooms() throws Exception {
        Integer patientId = registerPatient(1);

        // Create room
        String roomName = "Room 122";
        String roomInfo = "Small";
        int roomCapacity = 1;
        RoomDto roomDto = RoomDto.builder()
                .name(roomName)
                .info(roomInfo)
                .maxCapacity(roomCapacity)
                .build();
        String body = objectMapper.writeValueAsString(roomDto);
        String roomResourceUrl = mvc.perform(post(ROOMS_BASE_URI)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        String roomId = roomResourceUrl.substring(roomResourceUrl.lastIndexOf('/') + 1);

        // CheckIn patient
        mvc.perform(post(PATIENTS_BASE_URI + '/' + patientId + "/room/" + roomId)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Get room info
        mvc.perform(get(ROOMS_BASE_URI + '/' + roomId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(roomName)))
                .andExpect(jsonPath("$.info", is(roomInfo)))
                .andExpect(jsonPath("$.maxCapacity", is(roomCapacity)))
                .andExpect(jsonPath("$.patients", hasSize(1)))
                .andExpect(jsonPath("$.patients[0].name", is("Herminia")));

        // Checkout patient
        mvc.perform(delete(PATIENTS_BASE_URI + '/' + patientId + "/room")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Get room info
        mvc.perform(get(ROOMS_BASE_URI + '/' + roomId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(roomName)))
                .andExpect(jsonPath("$.info", is(roomInfo)))
                .andExpect(jsonPath("$.maxCapacity", is(roomCapacity)))
                .andExpect(jsonPath("$.patients", hasSize(0)));
    }

    @Test
    void registering_patient_should_check_max_room_capacity_is_not_reached() throws Exception {
        Integer patientId = registerPatient(2);

        RoomDto roomDto = RoomDto.builder()
                .name("Single Capacity Room")
                .info("Single Capacity Room Info")
                .maxCapacity(1)
                .build();
        String body = objectMapper.writeValueAsString(roomDto);
        String roomResourceUrl = mvc.perform(post(ROOMS_BASE_URI)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        String roomId = roomResourceUrl.substring(roomResourceUrl.lastIndexOf('/') + 1);

        // CheckIn patient
        mvc.perform(post(PATIENTS_BASE_URI + '/' + patientId + "/room/" + roomId)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        //Registering a new patient will give a different identifier
        patientId = registerPatient(3);

        mvc.perform(post(PATIENTS_BASE_URI + '/' + patientId + "/room/" + roomId)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());

    }

    private Integer registerPatient(Integer id) {
        Credential credential = Credential.builder()
                .username("user" + id)
                .hashedPassword("hashedPassword")
                .build();
        Patient patient = Patient.builder()
                .personalId("personalId" + id)
                .name("Herminia")
                .phone("phone" + id)
                .age(68)
                .credential(credential)
                .build();
        return patientRepository.save(patient).getId();
    }
}


