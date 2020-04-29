package com.korabelska.demo.repository.impl;

import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Statement;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.repository.BaseRepository;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class DepartmentRepositoryImpl extends BaseRepository<Department,String> {


    public DepartmentRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public Department create(Department department) {
        department.setId(UUID.randomUUID().toString());
        spannerTemplate.insert(department);
        return department;
    }

    @Override
    public Department updateExisting(Department department) {
        spannerTemplate.update(department);
        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = spannerTemplate.readAll(Department.class);
        return departments;
    }

    @Override
    public void delete(Department department) {
        spannerTemplate.delete(department);
    }

    @Override
    public void deleteById(String id) {
        spannerTemplate.delete(Department.class, Key.of(id));
    }

    public void deleteByIdAndHospitalId(String id,String hospitalId) {
//        spannerTemplate.query(Department.class,
//                Statement.of("DELETE FROM DEPARTMENTS WHERE DEPARTMENT_ID=\"" + id
//                        +"\" AND HOSPITAL_ID=\"" + hospitalId + "\""),null);
        spannerTemplate.delete(Department.class,Key.of(hospitalId,id));
    }

    @Override
    public boolean existsById(String id) {
        boolean exists = spannerTemplate.existsById(Department.class,Key.of(id));
        return exists;
    }

    @Override
    public Optional<Department> findById(String id) {
        Department department = spannerTemplate.read(Department.class,Key.of(id));
        return Optional.of(department);
    }

    public List<Department> findByHospitalId(String hospitalId) {
        List<Department> departments = spannerTemplate.query(Department.class, Statement.of("SELECT * FROM DEPARTMENTS WHERE HOSPITAL_ID=\""+hospitalId + "\""),null);
        return departments;
    }

    public Optional<Department> findByIdAndHospitalId(String id,String hospitalId) {
       Department department = spannerTemplate.read(Department.class,Key.of(hospitalId,id));
       return Optional.of(department);
    }
}
