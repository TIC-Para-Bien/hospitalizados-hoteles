package org.ticparabien.hotelcovid19.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Value("${spring.datasource.url}")
    private String connectionUrl;

}
