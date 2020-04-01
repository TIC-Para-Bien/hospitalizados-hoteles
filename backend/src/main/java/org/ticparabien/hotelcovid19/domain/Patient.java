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
@Table(name = "patient")
public class Patient extends User {

    @Column(name = "PERSONAL_ID", nullable = false)
    private String personalId;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @ToString.Exclude
    @OrderBy(value = "createdOn DESC")
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<HealthRecord> healthRecords;

    @JoinColumn(name = "ROOM_ID")
    @ManyToOne
    private Room room;
}
