package org.ticparabien.hotelcovid19.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ticparabien.hotelcovid19.domain.Credential;
import org.ticparabien.hotelcovid19.domain.repositories.CredentialRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class DbUserDetailsService implements UserDetailsService {

    // TODO Missing functional requirements from mobile field hospitals to model User repositories properly

    private final CredentialRepository credentialRepository;

    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String username) {
        Credential credential = credentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Patient with username: " + username + " was not found."));

        return new User(credential.getUsername(), credential.getHashedPassword(), getAuthority(credential));
    }

    private List<GrantedAuthority> getAuthority(Credential credential) {
        return credential.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
