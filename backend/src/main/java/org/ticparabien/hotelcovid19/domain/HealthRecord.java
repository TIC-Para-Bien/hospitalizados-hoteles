package org.ticparabien.hotelcovid19.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "health_records")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(name = "CREATED_ON")
    private Date createdOn;

    @JoinColumn(name = "PATIENT_ID")
    @ManyToOne(optional = false)
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
