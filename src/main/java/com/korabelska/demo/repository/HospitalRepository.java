package com.korabelska.demo.repository;

import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Hospital;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    @Query("SELECT id, name " +
            "FROM department where hospital=:id")
    List<Department> findDepartmentsByHospitalId(@Param("id") Long id);


}
