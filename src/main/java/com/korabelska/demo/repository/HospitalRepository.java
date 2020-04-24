package com.korabelska.demo.repository;

import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Hospital;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    @Query("SELECT id, name " +
            "FROM department where hospital=:id")
    List<Department> findDepartmentsByHospitalId(@Param("id") Long id);

    @Query("SELECT id, name " +
            "FROM department where hospital=:id and id")
    Optional<Department> findDepartmentByIdAndHospitalId(@Param("h_id") Long hospitalId,
                                                         @Param("d_id") Long departmentId);
}
