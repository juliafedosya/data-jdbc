package com.korabelska.demo.controller;

import com.korabelska.demo.dto.SaveDoctorDto;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.repository.DoctorRepository;
import com.korabelska.demo.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;



    @Autowired
    public DoctorController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Doctor>> getAllDoctors() {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllDoctors(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Object> createDoctor(@RequestBody SaveDoctorDto doctorDto) {
        Optional<Hospital> hospital = hospitalRepository.findById(doctorDto.getHospitalId());
        if(hospital.isPresent()) {
            Department department = hospitalRepository
                    .findDepartmentByIdAndHospitalId(doctorDto.getHospitalId(), doctorDto.getDepartmentId()).get();
        }
        return null;
    }


}
