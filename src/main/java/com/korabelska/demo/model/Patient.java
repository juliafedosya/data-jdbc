package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {


    private Long id;
    private String firstName;
    private LocalDate dateOfBirth;

    private List<PatientDiagnosis> patientDiagnoses;

    public static Patient create(String firstName, LocalDate dateOfBirth) {
        return new Patient(null, firstName, dateOfBirth, null);
    }

    public static Patient create(String firstName, LocalDate dateOfBirth,
                                 List<PatientDiagnosis> patientDiagnoses) {
        return new Patient(null, firstName, dateOfBirth, patientDiagnoses);
    }

    public void addPatientDiagnosis(PatientDiagnosis patientDiagnosis) {
        if(this.patientDiagnoses == null){
            patientDiagnoses = new ArrayList<>();
        }
        this.patientDiagnoses.add(patientDiagnosis);
    }


}
