package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PATIENTS")
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

    @Interleaved
    @EqualsAndHashCode.Exclude
    private List<PatientDiagnosis> patientDiagnoses;

}
