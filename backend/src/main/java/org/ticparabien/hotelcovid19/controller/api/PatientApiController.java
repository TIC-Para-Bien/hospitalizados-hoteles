package org.ticparabien.hotelcovid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.HighFeverDto;
import org.ticparabien.hotelcovid19.domain.actions.FindPatientsWithHighFever;
import org.ticparabien.hotelcovid19.domain.dto.PatientRequestByTemperatureAndDateDto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PatientApiController {

    private static final String FEVER_LIMIT = "37.7f";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private FindPatientsWithHighFever findPatientsWithHighFever;

    @GetMapping(Routes.HighFeverPatients)
    @ResponseStatus(HttpStatus.OK)
    public List<HighFeverDto> getHighFeverPatients(@RequestParam(defaultValue = FEVER_LIMIT) float temperature,
                                                   @RequestParam(required = true) @DateTimeFormat(pattern = DATE_FORMAT) Date date) {
        final PatientRequestByTemperatureAndDateDto dto = new PatientRequestByTemperatureAndDateDto(temperature, date);
        return findPatientsWithHighFever.execute(dto).stream()
                .map(healthRecord -> new HighFeverDto(healthRecord.getPatient().getId(), healthRecord.getTemperature()))
                .collect(Collectors.toList());
    }
}
