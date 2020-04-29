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
@Table(name = "DOCTORS")
public class Doctor {

    @Column(name = "HOSPITAL_ID")
    @PrimaryKey
    private String hospitalId;

    @Column(name = "DEPARTMENT_ID")
    @PrimaryKey(keyOrder = 2)
    private String departmentId;

    @Column(name = "DOCTOR_ID")
    @PrimaryKey(keyOrder = 3)
    private String doctorId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    public static Doctor create(String firstName, LocalDate dateOfBirth) {
        return new Doctor(null,null,null,firstName, dateOfBirth);
    }
}
