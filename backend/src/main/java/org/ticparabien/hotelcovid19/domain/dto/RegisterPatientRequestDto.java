package org.ticparabien.hotelcovid19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPatientRequestDto {

    private String personalId;

    private String phone;

    private String name;

    private Integer age;
}
