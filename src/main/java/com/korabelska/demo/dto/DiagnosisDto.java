package com.korabelska.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiagnosisDto {

    private String details;

    private String remarks;

    LocalDate dateConfirmed;

}
