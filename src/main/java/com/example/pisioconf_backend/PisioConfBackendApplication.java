package com.example.pisioconf_backend;

import com.example.pisioconf_backend.repositories.KorisnikRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.Model;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableJpaRepositories(basePackageClasses= KorisnikRepository.class)
public class PisioConfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PisioConfBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper;
    }
}
