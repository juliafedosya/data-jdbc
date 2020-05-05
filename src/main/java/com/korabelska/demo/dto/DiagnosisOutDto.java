package com.korabelska.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiagnosisOutDto {

    private String details;

    private String remarks;

    private String patientId;

    @JsonIgnore
    private String doctorId;

    LocalDate dateConfirmed;

}
