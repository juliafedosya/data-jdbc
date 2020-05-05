package com.korabelska.demo.controller;

import com.korabelska.demo.dto.DiagnosisDto;
import com.korabelska.demo.dto.PatientDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Patient;
import com.korabelska.demo.model.PatientDiagnosis;
import com.korabelska.demo.service.DoctorService;
import com.korabelska.demo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    private final DoctorService doctorService;

    @GetMapping()
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping(params = "hospitalId")
    public List<Patient> getAllPatientsByHospital(@RequestParam String hospitalId) {
        List<Patient> patients = patientService.findPatientsByHospitalId(hospitalId);
        return patients;
    }

    @GetMapping(path = "/{patientId}", params = {"hospitalId"})
    public ResponseEntity<Patient> getPatientById(@PathVariable String patientId,
                                                  @RequestParam String hospitalId) {
        Patient patient;
        try {
            patient = patientService.findByKey(hospitalId, patientId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{patientId}/diagnoses")
    public List<PatientDiagnosis> getAllDiagnosesByPatientId(@PathVariable String patientId) {
        List<PatientDiagnosis> patientDiagnoses = patientService.findPatientDiagnosesByPatientId(patientId);
        return patientDiagnoses;
    }

    @GetMapping(path = "/{patientId}/diagnoses/{diagnosisId}", params = {"hospitalId"})
    public ResponseEntity<PatientDiagnosis> getPatientDiagnosisByKey(@PathVariable String patientId,
                                                                     @PathVariable String diagnosisId,
                                                                     @RequestParam String hospitalId) {
        PatientDiagnosis patientDiagnosis;
        try {
            patientDiagnosis = patientService
                    .findPatientDiagnosisByKey(hospitalId, patientId, diagnosisId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDiagnosis);
    }

    @PostMapping(params = {"hospitalId"})
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto, String hospitalId) {
        Patient patient = patientService.create(patientDto, hospitalId);
        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PostMapping(path = "/{patientId}/diagnoses", params = {"hospitalId"})
    public ResponseEntity<PatientDiagnosis> createPatientDiagnosis(@PathVariable String patientId,
                                                                   @RequestBody DiagnosisDto diagnosisDto,
                                                                   @RequestParam String hospitalId) {
        PatientDiagnosis patientDiagnosis = patientService.createDiagnosis(diagnosisDto, hospitalId, patientId);
        return new ResponseEntity<>(patientDiagnosis, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{patientId}", params = {"hospitalId"})
    public ResponseEntity<Patient> updatePatient(@RequestParam String hospitalId, @PathVariable String patientId, @RequestBody PatientDto patientDto) {
        Patient patient;
        try {
            patient = patientService.update(hospitalId, patientId, patientDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    @PatchMapping(path = "/{patientId}/diagnoses/{diagnosisId}", params = {"hospitalId"})
    public ResponseEntity<PatientDiagnosis> updatePatientDiagnosis(@RequestParam String hospitalId, @PathVariable("patientId") String patientId,
                                                                   @PathVariable String diagnosisId, @RequestBody DiagnosisDto diagnosisDto) {
        PatientDiagnosis patientDiagnosis;
        try {
            patientDiagnosis = patientService.updateDiagnosis(diagnosisDto, hospitalId, patientId, diagnosisId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDiagnosis);

    }

    @DeleteMapping(path = "/{patientId}", params = {"hospitalId"})
    public ResponseEntity<Void> deletePatient(@RequestParam String hospitalId, @PathVariable String patientId) {
        patientService.delete(hospitalId, patientId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{patientId}/diagnoses/{diagnosisId}", params = {"hospitalId"})
    public ResponseEntity<Void> deleteDiagnosis
            (@RequestParam String hospitalId, @PathVariable String patientId, @PathVariable String diagnosisId) {
        patientService.deleteDiagnosis(hospitalId, patientId, diagnosisId);
        return ResponseEntity.noContent().build();
    }
}
