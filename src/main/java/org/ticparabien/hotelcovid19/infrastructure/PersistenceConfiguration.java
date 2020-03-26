package org.ticparabien.hotelcovid19.infrastructure;

import org.ticparabien.hotelcovid19.domain.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Value("${spring.datasource.url}")
    private String connectionUrl;

    @Bean
    public RoomRepository RoomRepository(){
        return new PostgresRoomRepository(connectionUrl);
    }

}
