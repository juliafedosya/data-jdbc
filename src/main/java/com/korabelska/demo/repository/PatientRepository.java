package com.korabelska.demo.repository;

import com.korabelska.demo.model.Patient;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PatientRepository extends CrudRepository<Patient, Long> {
    @Query("select id, first_name, dob,  from patient where upper(first_name) like '%' || upper(:name) || '%' ")
    List<Patient> findByName(@Param("name") String name);
}

