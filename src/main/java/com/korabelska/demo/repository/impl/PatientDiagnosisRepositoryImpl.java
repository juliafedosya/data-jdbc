package com.korabelska.demo.repository.impl;

import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Statement;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.PatientDiagnosis;
import com.korabelska.demo.repository.BaseRepository;
import org.springframework.cloud.gcp.data.spanner.core.SpannerTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Repository
public class PatientDiagnosisRepositoryImpl extends BaseRepository<PatientDiagnosis, String> {

    private static final Class<PatientDiagnosis> REPOSITORY_CLASS = PatientDiagnosis.class;

    public PatientDiagnosisRepositoryImpl(SpannerTemplate spannerTemplate) {
        super(spannerTemplate);
    }

    @Override
    public PatientDiagnosis create(PatientDiagnosis patientDiagnosis) {
        patientDiagnosis.setDiagnosisId(UUID.randomUUID().toString());
        spannerTemplate.insert(patientDiagnosis);
        return patientDiagnosis;
    }

    @Override
    public PatientDiagnosis updateExisting(PatientDiagnosis patientDiagnosis) throws EntityNotFoundException {
        Key key = Key.of(patientDiagnosis.getHospitalId(), patientDiagnosis.getPatientId(), patientDiagnosis.getDiagnosisId());
        if (spannerTemplate.existsById(REPOSITORY_CLASS, key)) {
            spannerTemplate.update(patientDiagnosis);
            return patientDiagnosis;
        }
        throw new EntityNotFoundException(patientDiagnosis.getDiagnosisId());
    }

    @Override
    public List<PatientDiagnosis> findAll() {
        List<PatientDiagnosis> patientDiagnoses = spannerTemplate.readAll(REPOSITORY_CLASS);
        return patientDiagnoses;
    }

    public List<PatientDiagnosis> findDiagnosisByPatientId(String patientId) {
        List<PatientDiagnosis> diagnoses = spannerTemplate.query(REPOSITORY_CLASS,
                Statement.of("SELECT * FROM PATIENT_DIAGNOSES WHERE PATIENT_ID=\"" + patientId + "\""),
                null);
        return diagnoses;
    }

    @Override
    public Optional<PatientDiagnosis> findByKey(String... keys) {
        PatientDiagnosis patientDiagnosis = spannerTemplate.read(REPOSITORY_CLASS, Key.of(keys));
        return Optional.ofNullable(patientDiagnosis);
    }

    @Override
    public void deleteByKey(String... keys) {
        spannerTemplate.delete(REPOSITORY_CLASS, Key.of(keys));
    }
}
