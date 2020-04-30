package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PATIENT_DIAGNOSES")
public class PatientDiagnosis {

    @PrimaryKey
    @Column(name = "HOSPITAL_ID")
    private String hospitalId;

    @PrimaryKey(keyOrder = 2)
    @Column(name = "PATIENT_ID")
    private String patientId;

    @PrimaryKey(keyOrder = 3)
    @Column(name = "PATIENT_ID")
    private String diagnosisId;

    @Column(name = "DOCTOR_ID")
    private String doctorId;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "DATE_CONFIRMED")
    LocalDate dateConfirmed;
}
