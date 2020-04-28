package com.korabelska.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

//    private final DoctorService doctorService;
//
//
//
//    @Autowired
//    public DoctorController(DoctorService doctorService, HospitalRepository hospitalRepository) {
//        this.doctorService = doctorService;
//        this.hospitalRepository = hospitalRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<Iterable<Doctor>> getAllDoctors() {
//        Iterable<Doctor> doctors = doctorService.findAll();
//        return ResponseEntity.ok(doctors);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getDoctorById(@PathVariable Long id) {
//        Optional<Doctor> doctor = doctorService.findById(id);
//        boolean isPresent = doctor.isPresent();
//
//        if (isPresent) {
//            return ResponseEntity.ok(doctor.get());
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<Object> createDoctor(@RequestBody DoctorDto doctorDto) {
//        Long hospitalId = doctorDto.getHospitalId();
//        Long departmentId = doctorDto.getDepartmentId();
//        Optional<Hospital> hospital = hospitalRepository.findById(hospitalId);
//
//        if (hospital.isPresent()) {
//            Optional<Department> department = hospital.get().getDepartments()
//                    .stream().filter(dept -> dept.getId().equals(departmentId))
//                    .findFirst();
//
//            if (department.isPresent()) {
//                Doctor doctor = doctorService.create(doctorDto, department.get(), hospital.get());
//                return ResponseEntity.ok(doctor);
//            }
//            return ResponseEntity.badRequest().build();
//        }
//        return null;
//    }
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
