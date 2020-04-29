package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HOSPITALS")
public class Hospital {

    @PrimaryKey
    @Column(name = "HOSPITAL_ID")
    private String id;

    @Column(name = "NAME")
    private  String name;

    @Column(name = "ADDRESS")
    private String address;

    @Interleaved
    @EqualsAndHashCode.Exclude
    private List<Department> departments;

    public static Hospital create(String name, String address,List<Department> departments) {
        Hospital hospital = new Hospital(null,name,address,departments);
        return hospital;
    }

    public void addDepartment(Department department) {
        if(departments == null) {
            departments = new ArrayList<>();
        }
        departments.add(department);
    }

}
