package com.korabelska.demo.repository.impl;

import com.google.cloud.spanner.Key;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.repository.BaseRepository;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class DoctorRepositoryImpl extends BaseRepository<Doctor, String> {

    public DoctorRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public Doctor create(Doctor doctor) {
        doctor.setDoctorId(UUID.randomUUID().toString());
         spannerTemplate.insert(doctor);
         return doctor;
    }

    @Override
    public Doctor updateExisting(Doctor doctor) {
        spannerTemplate.update(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = spannerTemplate.readAll(Doctor.class);
        return doctors;
    }

    @Override
    public void delete(Doctor doctor) {
        spannerTemplate.delete(doctor);
    }

    @Override
    public void deleteById(String id) {
        spannerTemplate.delete(Doctor.class,Key.of(id));
    }

    @Override
    public boolean existsById(String id) {
        boolean exists = spannerTemplate.existsById(Doctor.class,Key.of(id));
        return exists;
    }

    @Override
    public Optional<Doctor> findById(String id) {
        Doctor doctor = spannerTemplate.read(Doctor.class, Key.of(id));
        return Optional.of(doctor);
    }
}
