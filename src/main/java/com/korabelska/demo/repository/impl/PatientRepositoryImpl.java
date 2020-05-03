package com.korabelska.demo.repository.impl;

import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Statement;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.repository.BaseRepository;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class PatientRepositoryImpl extends BaseRepository<Patient,String> {

    private static final Class<Patient> REPOSITORY_CLASS = Patient.class;

    public PatientRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public Patient create(Patient patient) {
        patient.setPatientId(UUID.randomUUID().toString());
        spannerTemplate.insert(patient);
        return patient;
    }

    @Override
    public Patient updateExisting(Patient patient) throws EntityNotFoundException {
        Key key = Key.of(patient.getHospitalId(),patient.getPatientId());
        if(spannerTemplate.existsById(REPOSITORY_CLASS,key)) {
            spannerTemplate.update(patient);
            return patient;
        }
        throw new EntityNotFoundException(patient.getPatientId());
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = spannerTemplate.readAll(REPOSITORY_CLASS);
        return patients;
    }

    public List<Patient> findPatientsByHospitalId(String hospitalId) {
        List<Patient> patients = spannerTemplate.query(REPOSITORY_CLASS,
                Statement.of("SELECT HOSPITAL_ID,PATIENT_ID,FIRST_NAME,DATE_OF_BIRTH" +
                        " FROM PATIENTS WHERE HOSPITAL_ID=\"" + hospitalId + "\""),
                null);
        return patients;
    }

    @Override
    public Optional<Patient> findByKey(String... keys) {
        Patient patient = spannerTemplate.read(REPOSITORY_CLASS, Key.of(keys));
        return Optional.ofNullable(patient);
    }

    @Override
    public void deleteByKey(String... keys) {
        spannerTemplate.delete(REPOSITORY_CLASS,Key.of(keys));
    }
}
