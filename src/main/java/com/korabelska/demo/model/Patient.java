package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class Patient {

    @Id
    Long id;
    String firstName;
    LocalDate dateOfBirth;

    public static Patient create(String firstName, LocalDate dateOfBirth) {
        return new Patient(null, firstName,dateOfBirth);
    }




}
