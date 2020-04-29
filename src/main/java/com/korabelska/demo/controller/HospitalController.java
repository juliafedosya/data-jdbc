package com.korabelska.demo.controller;

import com.korabelska.demo.dto.CreateDepartmentDto;
import com.korabelska.demo.dto.HospitalDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Hospital hospital;
        try {
            hospital = hospitalService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hospital);
    }

    @GetMapping("/{id}/departments")
    public ResponseEntity<List<Department>> getDepartmentsByHospitalById(@PathVariable String id) {
        List<Department> departments;
        try {
            departments = hospitalService.findAllDepartmentsByHospitalId(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}/departments/{departmentId}")
    public ResponseEntity<Department> getDepartmentByIdAndHospitalId
            (@PathVariable("id") String hospitalId, @PathVariable String departmentId) {
        Department department;
        try {
             department = hospitalService.findDepartmentByIdHospitalId(departmentId, hospitalId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody HospitalDto hospitalDto) {
        Hospital hospital = hospitalService.create(hospitalDto);
        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/departments")
    public ResponseEntity<Hospital> createDepartment(@PathVariable("id") String hospitalId,
                                                     @RequestBody CreateDepartmentDto createDepartmentDto) {
        Hospital hospital;
        try {
            hospital = hospitalService.createDepartment(createDepartmentDto, hospitalId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(hospital,HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable String id,
                                                   @RequestBody HospitalDto hospitalDto) {
        Hospital hospital;
        try {
            hospital = hospitalService.update(hospitalDto, id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hospital);
    }
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
