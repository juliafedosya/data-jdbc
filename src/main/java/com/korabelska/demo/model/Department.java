package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEPARTMENTS")
public class Department {

    @PrimaryKey
    @Column(name = "HOSPITAL_ID")
    private String hospitalId;

    @PrimaryKey(keyOrder = 2)
    @Column(name = "DEPARTMENT_ID")
    private String id;

    @Column(name = "NAME")
    private String name;

//    @MappedCollection
//    private Set<Doctor> doctors;
//
//    public static Department create(String name,Set<Doctor> doctors) {
//        Department department = new Department(null,name,doctors);
//        return department;
//    }
//
//    public void addDoctor(Doctor doctor) {
//        if(doctors==null) {
//            doctors = new HashSet<>();
//        }
//        doctors.add(doctor);
//    }

}
