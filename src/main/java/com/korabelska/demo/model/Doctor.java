package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    private Long id;

    private String firstName;

    private LocalDate dateOfBirth;

    private Set<Patient> patients;

    public static Doctor create(String firstName, LocalDate dateOfBirth, Set<Patient> patients) {
        return new Doctor(null,firstName, dateOfBirth,patients);
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

}
