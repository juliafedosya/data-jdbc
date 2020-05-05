package com.korabelska.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
public class DoctorDiagnosisDto {

    private String firstName;

    private LocalDate dateOfBirth;

    private String departmentId;

    private String doctorId;

    @EqualsAndHashCode.Exclude
    private List<DiagnosisOutDto> diagnosisOutDtos;

}
