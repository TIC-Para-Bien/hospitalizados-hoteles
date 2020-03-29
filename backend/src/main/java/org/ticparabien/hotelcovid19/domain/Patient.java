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

    @ToString.Exclude
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<HealthRecord> healthRecords;

    @JoinColumn(name = "")
    @ManyToOne
    private Room room;

    public Patient(Integer id, String personalId, String phone, String name) {
        this.id = id;
        this.personalId = personalId;
        this.phone = phone;
        this.name = name;
    }
}
