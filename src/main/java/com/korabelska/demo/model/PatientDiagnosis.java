package com.korabelska.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class PatientDiagnosis {

    @Id
    private Long id;
    private String details;
    private String remarks;
    LocalDate dateConfirmed;
}
