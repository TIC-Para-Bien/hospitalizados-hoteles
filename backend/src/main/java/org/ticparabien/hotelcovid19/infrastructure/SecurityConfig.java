package org.ticparabien.hotelcovid19.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.ticparabien.hotelcovid19.controller.Routes;
import org.ticparabien.hotelcovid19.domain.Roles;

@Slf4j
@EnableWebSecurity
@EnableJdbcHttpSession
class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Configuration
    @Order(1)
    public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            log.debug("Setting security for user roles: {}", Roles.values());
            AuthenticationEntryPoint authenticationEntryPoint = new ApiAuthenticationEntryPoint();
            http.antMatcher("/api/**")
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
//                    .and()
//                    .authorizeRequests()
//                    .anyRequest().hasRole(Roles.USER.toString())
                    .and().csrf().disable();
        }
    }

    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage(Routes.login)
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/resources/**");
        }
    }
}

