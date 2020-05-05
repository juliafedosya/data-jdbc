package com.korabelska.demo.service;

import com.korabelska.demo.dto.DiagnosisOutDto;
import com.korabelska.demo.dto.DoctorDiagnosisDto;
import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.PatientDiagnosis;
import com.korabelska.demo.repository.impl.DoctorRepositoryImpl;
import com.korabelska.demo.repository.impl.PatientDiagnosisRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {


    private final DoctorRepositoryImpl doctorRepository;

    private final PatientDiagnosisRepositoryImpl diagnosisRepository;

    @Async
    public CompletableFuture<List<PatientDiagnosis>> getAllPatientDiagnoses() {
        List<PatientDiagnosis> patientDiagnoses = diagnosisRepository.findAll();
        return CompletableFuture.completedFuture(patientDiagnoses);
    }

    public Doctor findByKey(String hospitalId, String departmentId, String doctorId) throws EntityNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findByKey(hospitalId, departmentId, doctorId);
        return doctor.orElseThrow(() -> new EntityNotFoundException(doctorId));
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public List<DoctorDiagnosisDto> joinDoctorsAndDiagnoses() throws ExecutionException, InterruptedException {
        CompletableFuture<List<PatientDiagnosis>> completableFuture = this.getAllPatientDiagnoses();
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDiagnosisDto> doctorDiagnosisDtos = doctors.stream()
                .map(this::toDoctorDiagnosisDto).collect(Collectors.toList());
        List<PatientDiagnosis> diagnoses = completableFuture.get();
        List<DiagnosisOutDto> diagnosisOutDtos = diagnoses.stream()
                .map(this::toDiagnosisOutDto).collect(Collectors.toList());
        return join(doctorDiagnosisDtos,diagnosisOutDtos);
    }

    public Doctor create(String hospitalId, String departmentId, DoctorDto doctorDto) {
        Doctor doctor = toDoctor(doctorDto);
        doctor.setHospitalId(hospitalId);
        doctor.setDepartmentId(departmentId);
        return doctorRepository.create(doctor);
    }

    public Doctor update(String hospitalId, String departmentId, String doctorId, DoctorDto doctorDto) throws EntityNotFoundException {
        Doctor doctor = toDoctor(doctorDto);
        doctor.setHospitalId(hospitalId);
        doctor.setDepartmentId(departmentId);
        doctor.setDoctorId(doctorId);
        return doctorRepository.updateExisting(doctor);
    }

    public void delete(String hospitalId, String departmentId, String doctorId) {
        doctorRepository.deleteByKey(hospitalId, departmentId, doctorId);
    }

    private Doctor toDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctor.setFirstName(doctorDto.getFirstName());
        return doctor;
    }

    private DiagnosisOutDto toDiagnosisOutDto(PatientDiagnosis patientDiagnosis) {
        DiagnosisOutDto diagnosisOutDto = new DiagnosisOutDto();
        diagnosisOutDto.setDateConfirmed(patientDiagnosis.getDateConfirmed());
        diagnosisOutDto.setDetails(patientDiagnosis.getDetails());
        diagnosisOutDto.setRemarks(patientDiagnosis.getRemarks());
        diagnosisOutDto.setPatientId(patientDiagnosis.getPatientId());
        diagnosisOutDto.setDoctorId(patientDiagnosis.getDoctorId());
        return diagnosisOutDto;
    }

    private DoctorDiagnosisDto toDoctorDiagnosisDto(Doctor doctor) {
        DoctorDiagnosisDto doctorDiagnosisDto = new DoctorDiagnosisDto();
        doctorDiagnosisDto.setDateOfBirth(doctor.getDateOfBirth());
        doctorDiagnosisDto.setFirstName(doctor.getFirstName());
        doctorDiagnosisDto.setDepartmentId(doctor.getDepartmentId());
        doctorDiagnosisDto.setDoctorId(doctor.getDoctorId());
        return doctorDiagnosisDto;
    }

    private List<DoctorDiagnosisDto> join(List<DoctorDiagnosisDto> doctorDiagnosisDtos,
                                          List<DiagnosisOutDto> diagnosisOutDtos) {
        doctorDiagnosisDtos.forEach(doctorDiagnosisDto -> doctorDiagnosisDto
                .setDiagnosisOutDtos(
                diagnosisOutDtos.stream()
                .filter(diagnosisOutDto -> diagnosisOutDto.getDoctorId()
                .equals(doctorDiagnosisDto.getDoctorId())).collect(Collectors.toList())));
        return doctorDiagnosisDtos;
    }

}
