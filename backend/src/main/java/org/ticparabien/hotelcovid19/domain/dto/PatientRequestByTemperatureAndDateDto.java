package org.ticparabien.hotelcovid19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PatientRequestByTemperatureAndDateDto {

    private float temperature;

    private Date date;
}
