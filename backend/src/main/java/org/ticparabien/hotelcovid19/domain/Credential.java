package org.ticparabien.hotelcovid19.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "credentials")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "HASHED_PASSWORD", nullable = false)
    private String hashedPassword;

    @ManyToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name="CREDENTIAL_ROLES",
            joinColumns = {@JoinColumn(name="CREDENTIAL_ID", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName="id")}
    )
    private Set<Role> roles;
}
