package com.korabelska.demo.controller;

import com.korabelska.demo.dto.DoctorDiagnosisDto;
import com.korabelska.demo.dto.DoctorDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @GetMapping(path = "/{doctorId}", params = {
            "hospitalId",
            "departmentId"})
    public ResponseEntity<Object> getDoctorById(@RequestParam String hospitalId,
                                                @RequestParam String departmentId,
                                                @PathVariable String doctorId) {
        Doctor doctor;
        try {
            doctor = doctorService.findByKey(hospitalId, departmentId, doctorId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/diagnoses")
    public ResponseEntity<List<DoctorDiagnosisDto>> joinDoctorsAndDiagnoses() {
        try {
            return ResponseEntity.ok(doctorService.joinDoctorsAndDiagnoses());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(params = {
            "hospitalId",
            "departmentId"})
    public ResponseEntity<Doctor> createDoctor(@RequestParam String hospitalId,
                                               @RequestParam String departmentId,
                                               @RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorService.create(hospitalId, departmentId, doctorDto);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{doctorId}", params = {
            "hospitalId",
            "departmentId"})
    public ResponseEntity<Object> updateDoctor(@RequestParam String hospitalId,
                                               @RequestParam String departmentId,
                                               @PathVariable String doctorId,
                                               @RequestBody DoctorDto doctorDto) {
        Doctor doctor;
        try {
            doctor = doctorService.update(hospitalId, departmentId, doctorId, doctorDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping(path = "/{doctorId}", params = {
            "hospitalId",
            "departmentId"})
    public ResponseEntity<Void> deleteDoctor(@RequestParam String hospitalId,
                                             @RequestParam String departmentId,
                                             @PathVariable String doctorId) {
        doctorService.delete(hospitalId, departmentId, doctorId);
        return ResponseEntity.noContent().build();
    }
}
