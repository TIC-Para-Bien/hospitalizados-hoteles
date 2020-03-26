package org.ticparabien.hotelcovid19;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerTestConfig {
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("session", new SimpleThreadScope());
        return customScopeConfigurer;
    }
}