package com.korabelska.demo.service;

import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.repository.impl.DoctorRepositoryImpl;
import com.korabelska.demo.repository.impl.HospitalRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final HospitalRepositoryImpl hospitalRepository;

    private final DoctorRepositoryImpl doctorRepository;


    public Doctor findById(String id) throws EntityNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElseThrow(() -> new EntityNotFoundException(id));
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor create(DoctorDto doctorDto) {
        Doctor doctor = toDoctor(doctorDto);
        Doctor saved = doctorRepository.create(doctor);
        return saved;
    }
//
//    public Doctor save(Doctor doctor) {
//        return doctorRepository.save(doctor);
//    }
//
//    public Boolean addPatient(Long patientId,Long doctorId) {
//        return doctorRepository.addPatientToDoctor(patientId,doctorId);
//    }
//
//    public Doctor update(DoctorDto doctorDto, Doctor currentDoctor,
//                         Department department, Hospital hospital) {
//
//        if (!department.getDoctors().contains(currentDoctor)) {
//            department.addDoctor(currentDoctor);
//        }
//        currentDoctor.setFirstName(doctorDto.getFirstName());
//        currentDoctor.setDateOfBirth(doctorDto.getDateOfBirth());
//        doctorRepository.save(currentDoctor);
//        return currentDoctor;
//    }
//
//    public void delete(Long id) {
//        Boolean exists = doctorRepository.existsById(id);
//        if (exists) {
//            doctorRepository.deleteById(id);
//        }
//    }
//
    private Doctor toDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setHospitalId(doctorDto.getHospitalId());
        doctor.setDepartmentId(doctorDto.getDepartmentId());
        return doctor;
    }

}
