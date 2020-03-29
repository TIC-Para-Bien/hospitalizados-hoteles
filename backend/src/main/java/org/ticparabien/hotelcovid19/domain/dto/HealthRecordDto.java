package org.ticparabien.hotelcovid19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthRecordDto {

    private Integer id;

    private Integer patientId;

    private Float temperature;

    private Boolean cough;

    private Boolean headache;

    private Boolean throatAche;
}
