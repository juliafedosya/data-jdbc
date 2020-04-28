package com.korabelska.demo.repository.impl;

import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.repository.BaseRepository;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class DoctorRepositoryImpl extends BaseRepository<Doctor, String> {

    public DoctorRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public Doctor create(Doctor doctor) {
        return null;
    }

    @Override
    public Doctor updateExisting(Doctor doctor) {
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        return null;
    }

    @Override
    public void delete(Doctor doctor) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Optional<Doctor> findById(String s) {
        return Optional.empty();
    }
}
