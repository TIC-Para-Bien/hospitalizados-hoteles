package org.ticparabien.hotelcovid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.HighFeverDto;
import org.ticparabien.hotelcovid19.domain.actions.FindPatientsWithHighFever;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PatientApiController {

    @Autowired
    private FindPatientsWithHighFever findPatientsWithHighFever;

    @GetMapping(Routes.HighFeverPatients)
    @ResponseStatus(HttpStatus.OK)
    public List<HighFeverDto> getHighFeverPatients() {
        return findPatientsWithHighFever.execute().stream()
                .map(patient -> new HighFeverDto(patient.getId(), patient.temperature()))
                .collect(Collectors.toList());
    }
}
