package com.transporterbackend.Driver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DriverConfig {

    @Bean
    CommandLineRunner commandLineRunner(DriverRepository repository){
        return args -> {
            DriverModel demoDriver = new DriverModel(
                    "demo@driver.com",
                    "12345678",
                    "8810625561",
                    "Demo",
                    "Driver"
            );
            repository.saveAll(
                    List.of(demoDriver)
            );
        };
    }

}
