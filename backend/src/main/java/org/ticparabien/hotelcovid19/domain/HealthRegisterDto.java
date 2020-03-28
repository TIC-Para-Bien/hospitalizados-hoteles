package org.ticparabien.hotelcovid19.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthRegisterDto {

    private String patientId;

    private Float temperature;

    private Boolean cough;

    private Boolean headache;

    private Boolean throatAche;
}
