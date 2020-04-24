package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientDiagnosis {

    @Id
    private Long id;

    private String details;

    private String remarks;

    LocalDate dateConfirmed;
}
