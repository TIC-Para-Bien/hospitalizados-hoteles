package org.ticparabien.hotelcovid19.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "INFO")
    private String info;

    @Column(name = "MAX_CAPACITY")
    private Integer maxCapacity;

    @ToString.Exclude
    @OneToMany(mappedBy = "room")
    private List<Patient> patients;
}