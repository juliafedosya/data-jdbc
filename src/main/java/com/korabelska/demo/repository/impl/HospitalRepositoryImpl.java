package com.korabelska.demo.repository.impl;

import com.korabelska.demo.exceptions.EntityNotFoundException;
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

    private final Class<Hospital> repositoryClass = Hospital.class;

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
    public Hospital updateExisting(Hospital hospital) throws EntityNotFoundException {
        if(spannerTemplate.existsById(repositoryClass,Key.of(hospital.getId()))) {
            spannerTemplate.update(hospital);
            return hospital;
        }
        throw new EntityNotFoundException(hospital.getId());
    }

    @Override
    public List<Hospital> findAll() {
        List<Hospital> hospitals = spannerTemplate.readAll(repositoryClass);
        return hospitals;
    }

    @Override
    public Optional<Hospital> findByKey(String... keys) {
        Hospital hospital = spannerTemplate.read(repositoryClass,Key.of(keys));
        return Optional.ofNullable(hospital);
    }

    @Override
    public void deleteByKey(String... keys) {
        spannerTemplate.delete(repositoryClass,Key.of(keys));
    }
}
