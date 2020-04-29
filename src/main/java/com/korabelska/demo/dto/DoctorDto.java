package com.korabelska.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorDto {

    private String hospitalId;

    private String departmentId;

    private String firstName;

    private LocalDate dateOfBirth;
}
