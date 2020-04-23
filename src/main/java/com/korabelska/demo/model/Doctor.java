package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class Doctor {

    @Id
    Long id;
    String firstName;
    LocalDate dateOfBirth;
    Set<Patient> patients;

    public static Doctor create(String firstName, LocalDate dateOfBirth, Set<Patient> patients) {
        return new Doctor(null,firstName, dateOfBirth,patients);
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

}
