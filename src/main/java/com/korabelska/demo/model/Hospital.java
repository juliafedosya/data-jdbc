package com.korabelska.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
public class Hospital {

    @Id
    private Long id;
    private  String name;
    private String address;
    private Set<Department> departments;

}
