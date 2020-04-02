package org.ticparabien.hotelcovid19.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPersonnelRequestDto {

    private String personnelId;

    private String phone;

    private String name;
}
