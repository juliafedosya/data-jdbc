package com.korabelska.demo.model;

import lombok.*;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import java.util.List;

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
    private String departmentId;

    @Column(name = "NAME")
    private String name;

    @EqualsAndHashCode.Exclude
    @Interleaved
    private List<Doctor> doctors;

}
