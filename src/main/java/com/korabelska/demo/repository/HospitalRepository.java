package com.korabelska.demo.repository;

import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.model.Patient;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {
    @Query("SELECT id, name, address " +
            "FROM hospital where doctor in" +
            " (select id from doctor where upper(first_name) like '%' || upper(:name) || '%' )")
    List<Patient> findDepartmentsByHospitalName(@Param("name") String name);
}
