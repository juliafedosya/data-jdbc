package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {

    @Id
    private Long id;

    private  String name;

    private String address;

    @MappedCollection
    private Set<Department> departments;

    public static Hospital create(String name, String address,Set<Department> departments) {
        Hospital hospital = new Hospital(null,name,address,departments);
        return hospital;
    }

    public void addDepartment(Department department) {
        if(departments == null) {
            departments = new HashSet<>();
        }
        departments.add(department);
    }

}
