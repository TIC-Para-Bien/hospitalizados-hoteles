package org.ticparabien.hotelcovid19.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ticparabien.hotelcovid19.domain.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
}
