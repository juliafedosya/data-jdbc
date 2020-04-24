package com.korabelska.demo;

import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(DoctorRepository doctorRepository) {
		return args -> {
			Set<Patient> patients = new HashSet<>();
			Patient patient1 = Patient.create("Yen", LocalDate.of(2000,4,4));
			Patient patient2 = Patient.create("Chris",LocalDate.of(1990,2,12));
			patients.add(patient1);
			patients.add(patient2);
			Set<Patient> patients1 = new HashSet<>();
			Patient patient3 = Patient.create("Jeff",LocalDate.of(1990,5,5));
			patients1.add(patient3);
			Doctor doctor = Doctor.create("Ann",LocalDate.of(1990,6,6),patients);
			Doctor doctor1 = Doctor.create("Julia",LocalDate.of(1990,3,4),patients1);
			doctorRepository.save(doctor);
			doctorRepository.save(doctor1);

			log.info("{}",doctorRepository.findByName(doctor.getFirstName()));
			log.info("{}",doctorRepository.findPatientsByFirstName(doctor.getFirstName()));
			log.info("{}",doctorRepository.findPatientsByFirstName(doctor1.getFirstName()));

		};
	}
}
