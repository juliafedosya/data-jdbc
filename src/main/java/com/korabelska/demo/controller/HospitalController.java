package com.korabelska.demo.controller;

import com.korabelska.demo.dto.DepartmentDto;
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
    public List<Hospital> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.findAll();
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
            department = hospitalService.findDepartmentByIdAndHospitalId(departmentId, hospitalId);
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
                                                     @RequestBody DepartmentDto departmentDto) {
        Hospital hospital;
        try {
            hospital = hospitalService.createDepartment(departmentDto, hospitalId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
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

    @PatchMapping("/{id}/departments/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") String hospitalId,
                                                       @PathVariable String departmentId,
                                                       @RequestBody DepartmentDto departmentDto) {
        Department department;
        try {
            department = hospitalService.updateDepartment(departmentDto, departmentId, hospitalId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/{hospitalId}")
    public ResponseEntity<Void> deleteHospital(@PathVariable String hospitalId) {
        hospitalService.delete(hospitalId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{hospitalId}/departments/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String hospitalId,
                                                 @PathVariable String departmentId) {
        hospitalService.deleteDepartment(hospitalId, departmentId);
        return ResponseEntity.noContent().build();
    }
}
