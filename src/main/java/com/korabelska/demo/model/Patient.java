package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @PrimaryKey
    @Column(name = "HOSPITAL_ID")
    private String hospitalId;

    @PrimaryKey(keyOrder = 2)
    @Column(name = "PATIENT_ID")
    private String patientId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    private List<PatientDiagnosis> patientDiagnoses;

}
