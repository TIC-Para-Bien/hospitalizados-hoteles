package org.ticparabien.hotelcovid19.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.ticparabien.hotelcovid19.domain.Credential;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.Role;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;
import org.ticparabien.hotelcovid19.domain.repositories.RoleRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LoginPatientShould {

    private static final String loginPath = "/login";

    private static final String loginErrorPath = loginPath + "?error";

    private static final String loginParams = "?username=%s&password=%s&submitLogin=";

    private static final String sessionCookieName = "SESSION";

    private static final String phone = "123456";

    private static final String password = "password";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {

        patientRepository.deleteAll();
        registerPatient(phone, password);
    }

    @Test
    void a_registered_user_should_be_able_to_log_in() throws Exception {
        String params = String.format(loginParams, phone, password);
        Cookie cookie = mvc.perform(post(loginPath + params)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, "/"))
                .andReturn()
                .getResponse()
                .getCookie(sessionCookieName);

        assertThat(cookie.getPath(), is("/"));
        assertThat(cookie.isHttpOnly(), is(true));
        assertThat(cookie.getValue(), hasLength(48));
    }

    @Test
    void a_registered_user_that_provides_wrong_password_should_not_be_able_to_log_in() throws Exception {
        String wrongPassword = "aaaa";
        String params = String.format(loginParams, phone, wrongPassword);
        Cookie cookie = mvc.perform(post("/login" + params)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, loginErrorPath))
                .andReturn()
                .getResponse()
                .getCookie(sessionCookieName);

        assertThat(cookie.getPath(), is("/"));
        assertThat(cookie.isHttpOnly(), is(true));
        assertThat(cookie.getValue(), hasLength(48));
    }

    @Test
    void not_registered_users_should_not_be_able_to_log_in() throws Exception {
        String nonExistentUser = "user";
        String params = String.format(loginParams, nonExistentUser, password);
        Cookie cookie = mvc.perform(post("/login?" + params)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(header().string(HttpHeaders.LOCATION, loginErrorPath))
                .andReturn()
                .getResponse()
                .getCookie(sessionCookieName);

        assertThat(cookie.getPath(), is("/"));
        assertThat(cookie.isHttpOnly(), is(true));
        assertThat(cookie.getValue(), hasLength(48));
    }

    private void registerPatient(String phone, String password) {
        Role role = Role.builder()
                .name("ROLE")
                .build();
        Credential credential = Credential.builder()
                .username(phone)
                .hashedPassword(passwordEncoder.encode(password))
                .roles(Collections.singleton(role))
                .build();
        Patient patient = Patient.builder()
                .name("Angelines")
                .personalId("personalId")
                .age(88)
                .phone(phone)
                .credential(credential)
                .build();
        patientRepository.saveAndFlush(patient);
    }
}
