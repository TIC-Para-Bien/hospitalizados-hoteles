package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRepository;

@Service
@Component
public class AddHealthRegister {
    private final HealthRepository healthRepository;

    public AddHealthRegister(HealthRepository healthRepository) {
        this.healthRepository = healthRepository;
    }

    public void execute(HealthRegisterDto dto) {
        healthRepository.create(dto);
    }
}
