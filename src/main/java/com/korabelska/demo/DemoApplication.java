package com.korabelska.demo;

import com.korabelska.demo.model.*;
import com.korabelska.demo.repository.impl.HospitalRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(HospitalRepositoryImpl hospitalRepository) {
//        Hospital hospital = Hospital.create("george","address",null);
//        hospitalRepository.create(hospital);
//        hospital.setName("this must have been george");
//        hospitalRepository.updateExisting(hospital);
        return args -> {
        };
    }
}
