package com.korabelska.demo.repository.impl;

import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Statement;
import com.korabelska.demo.exceptions.EntityNotFoundException;
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

    private static final Class<Department> REPOSITORY_CLASS = Department.class;

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
    public Department updateExisting(Department department) throws EntityNotFoundException {
        Key key = Key.of(department.getHospitalId(),department.getDepartmentId());
        if(spannerTemplate.existsById(REPOSITORY_CLASS,key)) {
            spannerTemplate.update(department);
            return department;
        }
        throw new EntityNotFoundException(department.getDepartmentId());
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = spannerTemplate.readAll(REPOSITORY_CLASS);
        return departments;
    }

    @Override
    public Optional<Department> findByKey(String... keys) {
        Department department = spannerTemplate.read(REPOSITORY_CLASS,Key.of(keys));
        return Optional.ofNullable(department);
    }

    @Override
    public void deleteByKey(String... keys) {
        spannerTemplate.delete(REPOSITORY_CLASS, Key.of(keys));
    }

    public List<Department> findByHospitalId(String hospitalId) {
        List<Department> departments = spannerTemplate.query(REPOSITORY_CLASS, Statement.of("SELECT HOSPITAL_ID,DEPARTMENT_ID,NAME FROM DEPARTMENTS WHERE HOSPITAL_ID=\""+hospitalId + "\""),null);
        return departments;
    }

}
