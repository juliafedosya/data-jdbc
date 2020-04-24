package com.korabelska.demo.repository;

import com.korabelska.demo.model.Patient;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("select id, first_name, dob,  from patient where upper(first_name) like '%' || upper(:name) || '%' ")
    List<Patient> findByName(@Param("name") String name);

    @Modifying
    @Query("update patient set first_name = :name where id = :id")
    boolean updateName(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("update patient set doctor = :d_id where id = :p_id")
    boolean updateDoctor(@Param("d_id") Long doctorId,@Param("p_id") Long patientId);

    @Query("select patient_key from patient_diagnosis where patient = :p_id")
    List<Long> findPatientDiagnosisKeysByPatientId(@Param("p_id")Long patientId);
}

