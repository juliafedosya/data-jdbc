package com.korabelska.demo.repository.impl;

import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Statement;
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

    private final Class<Doctor> repositoryClass = Doctor.class;

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
        List<Doctor> doctors = spannerTemplate.readAll(repositoryClass);
        return doctors;
    }

    @Override
    public Optional<Doctor> findByKey(String... keys) {
        Doctor doctor = spannerTemplate.read(repositoryClass,Key.of(keys));
        return Optional.ofNullable(doctor);
    }

    @Override
    public void deleteByKey(String... keys) {
        spannerTemplate.delete(repositoryClass,Key.of(keys));
    }

    public Optional<Doctor> findByDoctorId(String doctorId) {
        List<Doctor> doctors = spannerTemplate.query(repositoryClass,
                Statement.of("SELECT * FROM DOCTORS WHERE DOCTOR_ID=\"" + doctorId + "\""),null);
        Optional<Doctor> optionalDoctor = doctors.stream().findFirst();
        return optionalDoctor;
    }
}
