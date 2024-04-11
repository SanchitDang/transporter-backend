package com.transporterbackend.Rider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RiderConfig {

    @Bean
    CommandLineRunner commandLineRunnerRider(RiderRepository repository){
        return args -> {
            RiderModel sanchit = new RiderModel(
                    "sanchit@outlook.com",
                    "12345678",
                    "8810625561",
                    "Sanchit",
                    "Dang"
            );
            RiderModel paras = new RiderModel(
                    "paras@outlook.com",
                    "12345678",
                    "8976787876",
                    "Paras",
                    "Kaushik"
            );
            repository.saveAll(
                    List.of(sanchit, paras)
            );
        };
    }

}
