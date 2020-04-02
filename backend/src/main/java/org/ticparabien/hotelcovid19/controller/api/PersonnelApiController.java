package org.ticparabien.hotelcovid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPersonnelRequestDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPersonnelResponseDto;
import org.ticparabien.hotelcovid19.domain.service.PersonnelService;

import javax.annotation.security.RolesAllowed;
import java.net.URI;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelApiController {

    @Autowired
    private PersonnelService personnelService;

    @RolesAllowed("ADMIN")
    @PostMapping
    public ResponseEntity<RegisterPersonnelResponseDto> registerPersonnel(@RequestBody RegisterPersonnelRequestDto dto) {
        RegisterPersonnelResponseDto responseDto = personnelService.createHealthPersonnel(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }
}
