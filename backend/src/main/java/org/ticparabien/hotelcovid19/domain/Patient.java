package org.ticparabien.hotelcovid19.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "HASHED_PASSWORD", nullable = false)
    private String hashedPassword;

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
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<HealthRecord> healthRecords;

    @JoinColumn(name = "ROOM_ID")
    @ManyToOne
    private Room room;
}
