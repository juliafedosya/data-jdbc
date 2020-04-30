package com.korabelska.demo.service;

import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.repository.impl.DoctorRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {


    private final DoctorRepositoryImpl doctorRepository;


    public Doctor findByKey( String hospitalId, String departmentId, String doctorId) throws EntityNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findByKey(hospitalId,departmentId,doctorId);
        return doctor.orElseThrow(() -> new EntityNotFoundException(doctorId));
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor create(DoctorDto doctorDto) {
        Doctor doctor = toDoctor(doctorDto);
        Doctor saved = doctorRepository.create(doctor);
        return saved;
    }

    public Doctor update(String doctorId, DoctorDto doctorDto) throws EntityNotFoundException {
        Doctor doctor = toDoctor(doctorDto);
        doctor.setDoctorId(doctorId);
        return doctorRepository.updateExisting(doctor);
    }

    public void delete(String hospitalId, String departmentId, String doctorId) {
        doctorRepository.deleteByKey(hospitalId,departmentId,doctorId);
    }

    private Doctor toDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setHospitalId(doctorDto.getHospitalId());
        doctor.setDepartmentId(doctorDto.getDepartmentId());
        return doctor;
    }

//    private Doctor toDoctor(Doctor doctor,DoctorDto doctorDto) {
//        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
//        doctor.setFirstName(doctorDto.getFirstName());
//        doctor.setHospitalId(doctorDto.getHospitalId());
//        doctor.setDepartmentId(doctorDto.getDepartmentId());
//        return doctor;
//    }

}
