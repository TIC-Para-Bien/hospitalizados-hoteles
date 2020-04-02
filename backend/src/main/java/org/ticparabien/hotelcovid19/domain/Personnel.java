package org.ticparabien.hotelcovid19.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "personnel")
public class Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PERSONAL_ID", nullable = false, unique = true)
    private String personnelId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Credential credential;
}
