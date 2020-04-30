package com.korabelska.demo.controller;

import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping(params = {
            "hospitalId",
            "departmentId",
            "doctorId"})
    public ResponseEntity<Object> getDoctorById(@RequestParam String hospitalId,
                                                @RequestParam String departmentId,
                                                @RequestParam String doctorId) {
        Doctor doctor;
        try {
            doctor = doctorService.findByKey(hospitalId, departmentId, doctorId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorService.create(doctorDto);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateDoctor(@PathVariable String id,
                                               @RequestBody DoctorDto doctorDto) {
        Doctor doctor;
        try {
           doctor = doctorService.update(id, doctorDto);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(params = {
            "hospitalId",
            "departmentId",
            "doctorId"})
    public ResponseEntity<Object> deleteDoctor(@RequestParam String hospitalId,
                                               @RequestParam String departmentId,
                                               @RequestParam String doctorId) {
        doctorService.delete(hospitalId, departmentId, doctorId);
        return ResponseEntity.ok().build();
    }
}
