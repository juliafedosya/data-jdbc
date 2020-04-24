package com.korabelska.demo.repository;

import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Patient;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query("select id, first_name, date_of_birth,  from doctor where upper(first_name) like '%' || upper(:name) || '%' ")
    List<Doctor> findByName(@Param("name") String name);

    @Query("SELECT id, first_name, date_of_birth " +
            "FROM patient where doctor in" +
            " (select id from doctor where upper(first_name) like '%' || upper(:name) || '%' )")
    List<Patient> findPatientsByFirstName(@Param("name") String name);


}
