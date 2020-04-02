package org.ticparabien.hotelcovid19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPersonnelResponseDto {

    private Integer id;

    private String password;
}
