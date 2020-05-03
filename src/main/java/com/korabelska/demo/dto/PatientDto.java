package com.korabelska.demo.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class PatientDto {

    private String firstName;

    private LocalDate dateOfBirth;

}
