package com.korabelska.demo.controller;

import com.korabelska.demo.dto.PatientDto;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.service.DoctorService;
import com.korabelska.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    private DoctorService doctorService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Patient>> getAllPatients() {
        Iterable<Patient> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getAllPatients(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);

        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto) {
        Optional<Doctor> optionalDoctor = doctorService.findById(patientDto.getDoctorId());
        if (optionalDoctor.isPresent()) {
            Patient patient = patientService.create(patientDto);
            Doctor assignedDoctor = optionalDoctor.get();
            assignedDoctor.addPatient(patient);
            doctorService.addPatient(patient.getId(),assignedDoctor.getId());
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable long id, @RequestBody PatientDto patientDto) {
        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isPresent()) {
            Optional<Doctor> optionalDoctor = doctorService.findById(patientDto.getDoctorId());

            if (optionalDoctor.isPresent()) {
                Patient updatedPatient = patientService.update(optionalPatient.get(),patientDto);

                Doctor doctor = optionalDoctor.get();
                if(!doctor.getPatients().contains(optionalPatient.get())){
                    doctorService.addPatient(updatedPatient.getId(),doctor.getId());
                }
                return ResponseEntity.ok(updatedPatient);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id ) {
        patientService.delete(id);
        return ResponseEntity.ok().build();
    }


}
