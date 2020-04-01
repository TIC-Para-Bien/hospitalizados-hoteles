package org.ticparabien.hotelcovid19.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "HASHED_PASSWORD", nullable = false)
    private String hashedPassword;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="user_roles",
            joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="ROLE_ID", referencedColumnName="id")}
    )
    private Set<Role> roles;
}
