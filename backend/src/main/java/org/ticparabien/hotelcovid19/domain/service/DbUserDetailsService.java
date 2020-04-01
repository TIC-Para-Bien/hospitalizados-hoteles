package org.ticparabien.hotelcovid19.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class DbUserDetailsService implements UserDetailsService {

    // TODO Missing functional requirements from mobile field hospitals to model User repositories properly

    private final PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Patient patient = patientRepository.findByPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException("Patient with username: " + username + " was not found."));

        return new User(patient.getPersonalId(), patient.getHashedPassword(), getAuthority());
    }

    private List<GrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
