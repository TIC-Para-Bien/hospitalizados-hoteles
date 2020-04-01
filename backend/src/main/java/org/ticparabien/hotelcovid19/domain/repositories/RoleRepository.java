package org.ticparabien.hotelcovid19.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(@Param("name") String name);
}
