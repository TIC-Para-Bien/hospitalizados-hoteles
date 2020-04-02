package org.ticparabien.hotelcovid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ticparabien.hotelcovid19.domain.dto.RoomDto;
import org.ticparabien.hotelcovid19.domain.service.RoomService;

import javax.annotation.security.RolesAllowed;
import java.net.URI;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {

    @Autowired
    private RoomService roomService;

    @RolesAllowed("PERSONNEL")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addNewRoom(@RequestBody RoomDto dto){
        Integer roomId = roomService.createRoom(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(roomId)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RolesAllowed("PERSONNEL")
    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("id") Integer id) {
        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RolesAllowed("PERSONNEL")
    @GetMapping
    public ResponseEntity<RoomDto> getRoomByName(@RequestParam(value = "name") String name) {
        return roomService.getRoomByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RolesAllowed("PERSONNEL")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoom(@PathVariable("id") Integer id, @RequestBody RoomDto dto){
        roomService.updateRoom(id, dto);
    }

    @RolesAllowed("PERSONNEL")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoom(@PathVariable("id") Integer id){
        roomService.deleteRoom(id);
    }
}
