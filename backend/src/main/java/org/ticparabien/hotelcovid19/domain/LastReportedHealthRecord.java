package org.ticparabien.hotelcovid19.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@EnableAutoConfiguration
@Table(name = "health_records")
public class LastReportedHealthRecord {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "CREATED_ON", nullable = false)
    private Date creationOn;
    @OneToOne(optional = false)
    private Patient patient;
    @Column(name = "TEMPERATURE", nullable = false)
    private Float temperature;
    @Column(name = "COUGH", nullable = false)
    private Boolean cough;
    @Column(name = "HEADACHE", nullable = false)
    private Boolean headache;
    @Column(name = "THROAT_ACHE", nullable = false)
    private Boolean throatAche;


    public LastReportedHealthRecord(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }
}
