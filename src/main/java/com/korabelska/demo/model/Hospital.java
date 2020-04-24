package com.korabelska.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

@Data
@AllArgsConstructor
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

}
