package org.ticparabien.hotelcovid19.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.Credential;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {

    @Query("FROM Credential c WHERE c.username = :username")
    Optional<Credential> findByUsername(@Param("username") String username);
}
