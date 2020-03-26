package org.ticparabien.hotelcovid19.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;
import org.ticparabien.hotelcovid19.domain.actions.AddHealthRegister;

@RestController
public class HealthApiController {
    @Autowired
    private AddHealthRegister addHealthRegister;

    @PostMapping("/register-health")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHealthRegister(HealthRegisterDto dto){
        addHealthRegister.execute(dto);
    }
}
