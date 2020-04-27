package com.korabelska.demo.service;

import com.korabelska.demo.dto.PatientDto;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.repository.DoctorRepository;
import com.korabelska.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    DoctorRepository doctorRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Patient> findAll() {
        Iterable<Patient> patients = patientRepository.findAll();
        return patients;
    }

    public Optional<Patient> findById(Long id) {
        Optional<Patient> patient= patientRepository.findById(id);
        return patient;
    }

    public Patient create(PatientDto patientDto) {
        Patient patient = this.toPatient(patientDto);
        patientRepository.save(patient);
        return patient;
    }

    public Patient update(Patient current,PatientDto patientDto){
        current.setFirstName(patientDto.getFirstName());
        current.setDateOfBirth(patientDto.getDateOfBirth());
        patientRepository.save(current);
        return current;
    }

    private Patient toPatient(PatientDto patientDto) {
        Patient patient = new Patient();
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setFirstName(patientDto.getFirstName());
        return patient;
    }

    public void delete(Long id) {
        Boolean exists = patientRepository.existsById(id);
        if (exists) {
            patientRepository.deleteById(id);
        }
    }

}
