package com.korabelska.demo.controller;

import com.korabelska.demo.dto.CreateDepartmentDto;
import com.korabelska.demo.dto.HospitalDto;
import com.korabelska.demo.dto.UpdateDepartmentDto;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public Iterable<Hospital> getAllHospitals() {
        Iterable<Hospital> hospitals = hospitalService.findAll();
        return hospitals;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable String id) {
        Optional<Hospital> hospital = hospitalService.findById(id);
        return hospital.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/departments")
    public ResponseEntity<List<Department>> getDepartmentsByHospitalById(@PathVariable String id) {
        Optional<Hospital> hospital = hospitalService.findById(id);

        if (hospital.isPresent()) {
            List<Department> departments = hospitalService.findAllDepartmentsByHospitalId(id);
            return ResponseEntity.ok(departments);
        }
        return ResponseEntity.notFound().build();
    }
//
//    @GetMapping("/{id}/departments/{departmentId}")
//    public ResponseEntity<Department> getDepartmentByIdAndHospitalId
//            (@PathVariable("id") String hospitalId, @PathVariable String departmentId) {
//        Optional<Hospital> hospital = hospitalService.findById(hospitalId);
//
//        if (hospital.isPresent()) {
//            Optional<Department> department = hospitalService
//                    .findDepartmentByIdAndHospitalId(hospitalId, departmentId);
//
//            if (department.isPresent()) {
//                return ResponseEntity.ok(department.get());
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<Hospital> createHospital(@RequestBody HospitalDto hospitalDto) {
//        Hospital hospital = hospitalService.create(hospitalDto);
//        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/{id}/departments")
//    public ResponseEntity<Hospital> createDepartment(@PathVariable("id") String hospitalId,
//                                                     @RequestBody CreateDepartmentDto createDepartmentDto) {
//        Optional<Hospital> optionalHospital = hospitalService.findById(hospitalId);
//
//        if (optionalHospital.isPresent()) {
//            Hospital hospital = hospitalService
//                    .createDepartment(createDepartmentDto, optionalHospital.get());
//            return new ResponseEntity<>(hospital, HttpStatus.CREATED);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id,
//                                                   @RequestBody HospitalDto hospitalDto) {
//        Optional<Hospital> optionalHospital = hospitalService.findById(id);
//        if (optionalHospital.isPresent()) {
//            Hospital hospital = hospitalService.update(optionalHospital.get(), hospitalDto);
//            return ResponseEntity.ok(hospital);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PatchMapping("/{id}/departments/{departmentId}")
//    public ResponseEntity<Hospital> updateDepartment(@PathVariable("id") Long hospitalId,
//                                                     @PathVariable Long departmentId,
//                                                     @RequestBody UpdateDepartmentDto departmentDto) {
//        Optional<Hospital> hospital = hospitalService.findById(hospitalId);
//
//        if (hospital.isPresent()) {
//            Optional<Department> department = hospitalService
//                    .findDepartmentByIdAndHospitalId(hospitalId, departmentId);
//
//            if (department.isPresent()) {
//                Hospital updated = hospitalService.updateDepartment(
//                        departmentDto, department.get(), hospital.get());
//                return ResponseEntity.ok(updated);
//            }
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteHospital(@PathVariable Long id) {
//        hospitalService.delete(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}/departments/{departmentId}")
//    public ResponseEntity<Object> deleteDepartment(@PathVariable("id") Long hospitalId,
//                                                   @PathVariable Long departmentId) {
//
//        Optional<Hospital> hospital = hospitalService.findById(hospitalId);
//
//        if (hospital.isPresent()) {
//            hospitalService.deleteDepartment(hospital.get(),departmentId);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
}
