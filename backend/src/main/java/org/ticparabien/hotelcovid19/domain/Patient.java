package org.ticparabien.hotelcovid19.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@EnableAutoConfiguration
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue
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

    public Integer getId() {
        return id;
    }

    public void setLastReportedHealthRecord(LastReportedHealthRecord lastReportedHealthRecord) {
        this.lastReportedHealthRecord = lastReportedHealthRecord;
    }
}
