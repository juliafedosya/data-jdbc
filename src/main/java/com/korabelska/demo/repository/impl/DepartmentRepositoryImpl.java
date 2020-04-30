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

    private final Class<Department> repositoryClass = Department.class;

    public DepartmentRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public Department create(Department department) {
        department.setDepartmentId(UUID.randomUUID().toString());
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
        List<Department> departments = spannerTemplate.readAll(repositoryClass);
        return departments;
    }

    @Override
    public Optional<Department> findByKey(String... keys) {
        Department department = spannerTemplate.read(repositoryClass,Key.of(keys));
        return Optional.ofNullable(department);
    }

    @Override
    public void deleteByKey(String... keys) {
        spannerTemplate.delete(repositoryClass, Key.of(keys));
    }

    public List<Department> findByHospitalId(String hospitalId) {
        List<Department> departments = spannerTemplate.query(repositoryClass, Statement.of("SELECT * FROM DEPARTMENTS WHERE HOSPITAL_ID=\""+hospitalId + "\""),null);
        return departments;
    }

}
