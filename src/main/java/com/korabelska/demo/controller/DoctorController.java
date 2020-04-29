package com.korabelska.demo.controller;

import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    @GetMapping
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        return doctors;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable String id) {
        Doctor doctor;
        try {
            doctorService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorService.create(doctorDto);
        return doctor;
    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Object> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto) {
//        Optional<Doctor> doctor = doctorService.findById(id);
//
//        if (doctor.isPresent()) {
//            Long hospitalId = doctorDto.getHospitalId();
//            Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
//
//            if (hospital.isPresent()) {
//                Long departmentId = doctorDto.getDepartmentId();
//                Optional<Department> department = hospital.get().getDepartments()
//                        .stream().filter(dept -> dept.getId().equals(departmentId))
//                        .findFirst();
//
//                if (department.isPresent()) {
//                    Doctor updated = doctorService.update(doctorDto,
//                            doctor.get(), department.get(), hospital.get());
//                    return ResponseEntity.ok(updated);
//                }
//            } else {
//                return ResponseEntity.badRequest().build();
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteDoctor(@PathVariable Long id) {
//        doctorService.delete(id);
//        return ResponseEntity.ok().build();
//    }
}
