package com.korabelska.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
public class Department {

    @Id
    private Long id;

    private Set<Doctor> doctors;

    private String name;

}
