package org.ticparabien.hotelcovid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.HighFeverDto;
import org.ticparabien.hotelcovid19.domain.actions.*;
import org.ticparabien.hotelcovid19.domain.dto.PatientDto;
import org.ticparabien.hotelcovid19.domain.dto.PatientRequestByTemperatureAndDateDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientRequestDto;
import org.ticparabien.hotelcovid19.domain.dto.RegisterPatientResponseDto;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PatientApiController {

    private static final String FEVER_LIMIT = "37.7f";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private FindPatientsWithHighFever findPatientsWithHighFever;

    @Autowired
    private RegisterPatient registerPatient;

    @Autowired
    private FindPatientById findPatientById;

    @Autowired
    private FindAllPatients findAllPatients;

    @Autowired
    private CheckInPatient checkInPatient;

    @Autowired
    private CheckOutPatient checkOutPatient;

    @GetMapping(Routes.HighFeverPatients)
    @ResponseStatus(HttpStatus.OK)
    public List<HighFeverDto> getHighFeverPatients(@RequestParam(defaultValue = FEVER_LIMIT) float temperature,
                                                   @RequestParam(required = true) @DateTimeFormat(pattern = DATE_FORMAT) Date date) {
        final PatientRequestByTemperatureAndDateDto dto = new PatientRequestByTemperatureAndDateDto(temperature, date);
        return findPatientsWithHighFever.execute(dto).stream()
                .map(healthRecord -> new HighFeverDto(healthRecord.getPatient().getId(), healthRecord.getTemperature()))
                .collect(Collectors.toList());
    }

    @PostMapping("/api/patients")
    public ResponseEntity<RegisterPatientResponseDto> registerPatient(@RequestBody RegisterPatientRequestDto dto) {
        RegisterPatientResponseDto responseDto = registerPatient.execute(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @GetMapping("/api/patients")
    public ResponseEntity<List<PatientDto>> getAllPatients(@RequestParam(required = false) Integer older) {
        List<PatientDto> patients = findAllPatients.execute();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/api/patients/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") Integer id) {
        return findPatientById.execute(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/patients/{id}/room/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void roomCheckIn(@PathVariable("id") Integer patientId, @PathVariable("roomId") Integer roomId) {
        checkInPatient.execute(patientId, roomId);
    }

    @DeleteMapping("/api/patients/{id}/room")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void roomCheckOut(@PathVariable("id") Integer patientId) {
        checkOutPatient.execute(patientId);
    }
}
