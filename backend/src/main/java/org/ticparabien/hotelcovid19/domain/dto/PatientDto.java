package org.ticparabien.hotelcovid19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ticparabien.hotelcovid19.domain.HealthRegisterDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Integer id;

    private String personalId;

    private String phone;

    private String name;

    private List<HealthRegisterDto> healthRecords;
}
