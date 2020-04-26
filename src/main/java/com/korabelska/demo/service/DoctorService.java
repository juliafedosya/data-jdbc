package com.korabelska.demo.service;

import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.repository.DoctorRepository;
import com.korabelska.demo.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    HospitalRepository hospitalRepository;

    DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(HospitalRepository hospitalRepository, DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
    }

    public Optional<Doctor> getDoctorById(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor;
    }

    public Iterable<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(DoctorDto doctorDto, Department department, Hospital hospital) {
        Doctor doctor = toDoctor(doctorDto);
        department.addDoctor(doctor);
        hospitalRepository.save(hospital);
       Doctor saved = doctorRepository.save(doctor);
       return saved;
    }

    public Doctor updateDoctor( DoctorDto doctorDto,Doctor currentDoctor,
                               Department department, Hospital hospital) {

        if(!department.getDoctors().contains(currentDoctor)){
            department.addDoctor(currentDoctor);
        }
            currentDoctor.setFirstName(doctorDto.getFirstName());
        currentDoctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctorRepository.save(currentDoctor);
        return currentDoctor;
    }

    public void deleteDoctor(Long id) {
        Boolean exists = doctorRepository.existsById(id);
        if(exists) {
            doctorRepository.deleteById(id);
        }
    }

    private Doctor toDoctor(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDateOfBirth(doctorDto.getDateOfBirth());
        doctor.setFirstName(doctorDto.getFirstName());
        return doctor;
    }

}
