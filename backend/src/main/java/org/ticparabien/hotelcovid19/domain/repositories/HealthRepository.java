package org.ticparabien.hotelcovid19.domain.repositories;

import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;

public interface HealthRepository {
    void create(HealthRegisterDto dto);
}
