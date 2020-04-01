package org.ticparabien.hotelcovid19.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity
@Table(name = "personnel")
public class Personnel extends User {

    @Column(name = "PERSONAL_ID", nullable = false, unique = true)
    private String personnelId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;
}
