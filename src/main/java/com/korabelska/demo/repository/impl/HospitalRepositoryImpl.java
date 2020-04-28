package com.korabelska.demo.repository.impl;

import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.repository.BaseRepository;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import com.google.cloud.spanner.Key;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class HospitalRepositoryImpl extends BaseRepository<Hospital,String> {

    public HospitalRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public Hospital create(Hospital hospital) {
        hospital.setId(UUID.randomUUID().toString());
        spannerTemplate.insert(hospital);
        return hospital;
    }

    @Override
    public Hospital updateExisting(Hospital hospital) {
        if(spannerTemplate.existsById(Hospital.class,Key.of(hospital.getId()))) {
            spannerTemplate.update(hospital);
        }
        return hospital;
    }

    @Override
    public List<Hospital> findAll() {
        List<Hospital> hospitals = spannerTemplate.readAll(Hospital.class);
        return hospitals;
    }

    @Override
    public void delete(Hospital hospital) {
        spannerTemplate.delete(hospital);
    }

    @Override
    public void deleteById(String id) {
        spannerTemplate.delete(Hospital.class,Key.of(id));
    }

    @Override
    public boolean existsById(String id) {
        boolean exists = spannerTemplate.existsById(Hospital.class,Key.of(id));
        return exists;
    }

    @Override
    public Optional<Hospital> findById(String id){
        Hospital hospital = spannerTemplate.read(Hospital.class,Key.of(id));
        return Optional.of(hospital);
    }
}
