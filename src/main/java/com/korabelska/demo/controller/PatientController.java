package com.korabelska.demo.controller;

import com.korabelska.demo.dto.DiagnosisDto;
import com.korabelska.demo.dto.PatientDto;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.model.PatientDiagnosis;
import com.korabelska.demo.service.DoctorService;
import com.korabelska.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{id}/diagnoses")
    public ResponseEntity<List<PatientDiagnosis>> getAllDiagnosesByPatientId(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientService.findById(id);
        if(optionalPatient.isPresent()){
            List<PatientDiagnosis> patientDiagnoses = patientService.
                    findPatientDiagnosesByPatientId(id);
            return ResponseEntity.ok(patientDiagnoses);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/diagnoses/{diagnosisId}")
    public ResponseEntity<PatientDiagnosis> getPatientDiagnosisByIdAndPatientId(@PathVariable Long id,@PathVariable Long diagnosisId) {
        Optional<Patient> optionalPatient = patientService.findById(id);

        if(optionalPatient.isPresent()){
            Optional<PatientDiagnosis> optionalDiagnosis = patientService.
                    findPatientDiagnosisByIdAndPatientId(id,diagnosisId);

            if(optionalDiagnosis.isPresent()) {
                return ResponseEntity.ok(optionalDiagnosis.get());
            }
        }
        return ResponseEntity.notFound().build();
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

    @PostMapping("/{id}/diagnoses")
    public ResponseEntity<Patient> createPatientDiagnosis(@PathVariable("id") Long patientId,
                                                                   @RequestBody DiagnosisDto diagnosisDto ) {
        Optional<Patient> optionalPatient = patientService.findById(patientId);
        if(optionalPatient.isPresent()) {
            Patient updatedPatient = patientService.createDiagnosis(diagnosisDto,optionalPatient.get());
            return ResponseEntity.ok(updatedPatient);
        }
        return ResponseEntity.notFound().build();
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

    @PatchMapping("/{id}/diagnoses/{diagnosisId}")
    public ResponseEntity<Patient> updatePatientDiagnosis(@PathVariable("id") Long patientId,
            @PathVariable Long diagnosisId,@RequestBody DiagnosisDto diagnosisDto) {

        Optional<Patient> optionalPatient = patientService.findById(patientId);

        if(optionalPatient.isPresent()){
            Optional<PatientDiagnosis> optionalDiagnosis = patientService.
                    findPatientDiagnosisByIdAndPatientId(patientId,diagnosisId);

            if(optionalDiagnosis.isPresent()) {
                Patient updatedPatient = patientService.updateDiagnosis(diagnosisDto,
                        optionalDiagnosis.get(),optionalPatient.get());
                return ResponseEntity.ok(updatedPatient);
            }
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id ) {
        patientService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/diagnoses/{diagnosisId}")
    public ResponseEntity<Object> deleteDiagnosis
            (@PathVariable("id") Long patientId,@PathVariable Long diagnosisId ) {

        Optional<Patient> optionalPatient = patientService.findById(patientId);

        if(optionalPatient.isPresent()){
            Optional<PatientDiagnosis> optionalDiagnosis = patientService.
                    findPatientDiagnosisByIdAndPatientId(patientId,diagnosisId);
            patientService.deleteDiagnosis(optionalPatient.get(),diagnosisId);
        }
        return ResponseEntity.ok().build();
    }
}
