package org.ticparabien.hotelcovid19.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EnableAutoConfiguration
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PERSONAL_ID", nullable = false)
    private String personalId;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(mappedBy = "patient")
    private LastReportedHealthRecord lastReportedHealthRecord;

    public Patient(Integer id, String personalId, String phone, String name) {
        this.id = id;
        this.personalId = personalId;
        this.phone = phone;
        this.name = name;
    }

    public float temperature() {
        return lastReportedHealthRecord.getTemperature();
    }
}
