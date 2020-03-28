package org.ticparabien.hotelcovid19.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EnableAutoConfiguration
@Table(name = "health_records")
public class LastReportedHealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "CREATED_ON")
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
}
