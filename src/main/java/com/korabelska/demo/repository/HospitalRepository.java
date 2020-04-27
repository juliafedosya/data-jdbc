package com.korabelska.demo.repository;

import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Hospital;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    @Query("SELECT * " +
            "FROM department where hospital=:id")
    List<Department> findDepartmentsByHospitalId(@Param("id") Long id);

    @Query("SELECT * " +
            "FROM department where hospital=:h_id and id=:d_id")
    Optional<Department> findDepartmentByIdAndHospitalId(@Param("h_id") Long hospitalId,
                                                         @Param("d_id") Long departmentId);

//    @Modifying
//    @Query("update doctor set d")
}
