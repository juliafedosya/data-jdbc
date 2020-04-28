package com.korabelska.demo.service;

import com.korabelska.demo.dto.CreateDepartmentDto;
import com.korabelska.demo.dto.HospitalDto;
import com.korabelska.demo.dto.UpdateDepartmentDto;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.repository.impl.DepartmentRepositoryImpl;
import com.korabelska.demo.repository.impl.HospitalRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepositoryImpl hospitalRepository;

    private final DepartmentRepositoryImpl departmentRepository;


    public Iterable<Hospital> findAll() {
        Iterable<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals;
    }

    public Optional<Hospital> findById(String id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        return hospital;
    }

    public List<Department> findAllDepartmentsByHospitalId(String id) {
        List<Department> departments = departmentRepository.findByHospitalId(id);
        return departments;
    }

//    public Optional<Department> findDepartmentByIdAndHospitalId(Long hospitalId, Long departmentId) {
//        Optional<Department> department = hospitalRepository
//                .findDepartmentByIdAndHospitalId(hospitalId,departmentId);
//        return department;
//    }
//
//    public Hospital create(HospitalDto hospitalDto) {
//        Hospital hospital = this.toHospital(new Hospital(),hospitalDto);
//        hospitalRepository.save(hospital);
//        return hospital;
//    }
//
//    public Hospital createDepartment(CreateDepartmentDto createDepartmentDto, Hospital hospital) {
//        Department department = toDepartment(new Department(), createDepartmentDto);
//        hospital.addDepartment(department);
//        hospitalRepository.save(hospital);
//        return hospital;
//    }
//
//    public Hospital update(Hospital hospital,HospitalDto hospitalDto) {
//        hospital = toHospital(hospital,hospitalDto);
//        hospitalRepository.save(hospital);
//        return hospital;
//    }
//
//    public Hospital updateDepartment(UpdateDepartmentDto departmentDto,Department department,Hospital hospital) {
//        hospital.getDepartments().remove(department);
//        department = toDepartment(department,departmentDto);
//        hospital.getDepartments().add(department);
//        hospitalRepository.save(hospital);
//        return hospital;
//    }
//
//    public void delete(Long hospitalId) {
//        if(hospitalRepository.existsById(hospitalId)) {
//            hospitalRepository.deleteById(hospitalId);
//        }
//    }
//
//    public void deleteDepartment(Hospital hospital,String departmentId) {
//        Optional<Department> matchingDepartment = hospital.getDepartments().stream().
//                filter(pd -> pd.getId().equals(departmentId)).
//                findFirst();
//
//        if(matchingDepartment.isPresent()) {
//            hospital.getDepartments().remove(matchingDepartment.get());
//            hospitalRepository.save(hospital);
//        }
//    }
//
//    private Hospital toHospital(Hospital hospital, HospitalDto hospitalDto) {
//        hospital.setAddress(hospitalDto.getAddress());
//        hospital.setName(hospitalDto.getName());
//        return hospital;
//    }
//
//    private Department toDepartment(Department department, CreateDepartmentDto createDepartmentDto) {
//        department.setName(createDepartmentDto.getName());
//        return department;
//    }
//
//    private Department toDepartment(Department department, UpdateDepartmentDto departmentDto) {
//        department.setName(departmentDto.getName());
//        Set<Long> doctorIds = departmentDto.getDoctorIds();
//
//        if(!doctorIds.isEmpty()) {
//            for(Long doctorId : doctorIds) {
//                Optional<Doctor> optDoctor = doctorRepository.findById(doctorId);
//                if(optDoctor.isPresent()) {
//                    department.addDoctor(optDoctor.get());
//                    doctorRepository.changeDepartment(department.getId(), optDoctor.get().getId());
//                }
//            }
//        }
//        return department;
//    }




}
