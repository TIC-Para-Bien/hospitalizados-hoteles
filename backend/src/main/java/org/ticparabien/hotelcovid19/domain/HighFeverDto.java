package org.ticparabien.hotelcovid19.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HighFeverDto {
    private final Integer patientId;
    private final float temperature;

}
