package com.korabelska.demo.service;

import com.korabelska.demo.dto.DiagnosisDto;
import com.korabelska.demo.dto.PatientDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.model.PatientDiagnosis;
import com.korabelska.demo.repository.impl.PatientDiagnosisRepositoryImpl;
import com.korabelska.demo.repository.impl.PatientRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepositoryImpl patientRepository;

    private final PatientDiagnosisRepositoryImpl patientDiagnosisRepository;

    public List<Patient> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    public List<Patient> findPatientsByHospitalId(String hospitalId) {
        List<Patient> patients = patientRepository.findPatientsByHospitalId(hospitalId);
        return patients;
    }

    public Patient findByKey(String hospitalId, String patientId) throws EntityNotFoundException {
        Optional<Patient> patient = patientRepository.findByKey(hospitalId, patientId);
        return patient.orElseThrow(() -> new EntityNotFoundException(patientId));
    }

    public List<PatientDiagnosis> findPatientDiagnosesByPatientId(String patientId) {
        List<PatientDiagnosis> patientDiagnoses = patientDiagnosisRepository.findDiagnosisByPatientId(patientId);
        return patientDiagnoses;
    }

    public PatientDiagnosis findPatientDiagnosisByKey(String hospitalId, String patientId, String patientDiagnosisId) throws EntityNotFoundException {
        Optional<PatientDiagnosis> patientDiagnoses = patientDiagnosisRepository
                .findByKey(hospitalId,patientId,patientDiagnosisId);
        return patientDiagnoses.orElseThrow(() -> new EntityNotFoundException(patientDiagnosisId));
    }

    public Patient create(PatientDto patientDto, String hospitalId) {
        Patient patient = this.toPatient(patientDto);
        patient.setHospitalId(hospitalId);
        patientRepository.create(patient);
        return patient;
    }

    public PatientDiagnosis createDiagnosis(DiagnosisDto diagnosisDto, String hospitalId, String patientId) {
        PatientDiagnosis diagnosis = toPatientDiagnosis(diagnosisDto);
        diagnosis.setPatientId(patientId);
        diagnosis.setHospitalId(hospitalId);

        return patientDiagnosisRepository.create(diagnosis);
    }

    public Patient update(String hospitalId,String patientId, PatientDto patientDto) throws EntityNotFoundException {
        Patient patient = toPatient(patientDto);
        patient.setHospitalId(hospitalId);
        patient.setPatientId(patientId);

        return patientRepository.updateExisting(patient);
    }

    public PatientDiagnosis updateDiagnosis(DiagnosisDto diagnosisDto, String hospitalId, String patientId, String diagnosisId) throws EntityNotFoundException {
        PatientDiagnosis diagnosis = this.toPatientDiagnosis(diagnosisDto);
        diagnosis.setHospitalId(hospitalId);
        diagnosis.setPatientId(patientId);
        diagnosis.setDiagnosisId(diagnosisId);

        return patientDiagnosisRepository.updateExisting(diagnosis);
    }

    public void delete(String hospitalId, String patientId) {
        patientRepository.deleteByKey(hospitalId, patientId);
    }

    public void deleteDiagnosis(String hospitalId, String patientId, String diagnosisId) {
        patientDiagnosisRepository.deleteByKey(hospitalId, patientId, diagnosisId);
    }

    private Patient toPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setFirstName(patientDto.getFirstName());
        return patient;
    }

    private PatientDiagnosis toPatientDiagnosis(DiagnosisDto diagnosisDto) {
        PatientDiagnosis diagnosis = new PatientDiagnosis();
        diagnosis.setDateConfirmed(diagnosisDto.getDateConfirmed());
        diagnosis.setDetails(diagnosisDto.getDetails());
        diagnosis.setRemarks(diagnosisDto.getRemarks());
        diagnosis.setDoctorId(diagnosisDto.getDoctorId());
        return diagnosis;
    }

}
