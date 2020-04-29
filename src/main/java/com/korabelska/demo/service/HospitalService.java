package com.korabelska.demo.service;

import com.korabelska.demo.dto.CreateDepartmentDto;
import com.korabelska.demo.dto.HospitalDto;
import com.korabelska.demo.exceptions.EntityNotFoundException;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.repository.impl.DepartmentRepositoryImpl;
import com.korabelska.demo.repository.impl.HospitalRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepositoryImpl hospitalRepository;

    private final DepartmentRepositoryImpl departmentRepository;


    public Iterable<Hospital> findAll() {
        Iterable<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals;
    }

    public Hospital findById(String id) throws EntityNotFoundException {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        return hospital.orElseThrow(()->new EntityNotFoundException(id));
    }

    public List<Department> findAllDepartmentsByHospitalId(String id) throws EntityNotFoundException {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        List<Department> departments = hospital.getDepartments();
        return departments;
    }

    public Department findDepartmentByIdHospitalId(String id,String hospitalId) throws EntityNotFoundException {
        Department department = departmentRepository.findByIdAndHospitalId(id,hospitalId);
        return department;
    }

    public Hospital create(HospitalDto hospitalDto) {
        Hospital hospital = this.toHospital(new Hospital(),hospitalDto);
        hospitalRepository.create(hospital);
        return hospital;
    }

    public Hospital createDepartment(CreateDepartmentDto createDepartmentDto,String hospitalId)
            throws EntityNotFoundException {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException(hospitalId));
        Department department = toDepartment(new Department(), createDepartmentDto);
        department.setHospitalId(hospitalId);
        hospital.addDepartment(departmentRepository.create(department));
        return hospital;
    }


    public Hospital update(HospitalDto hospitalDto,String id) throws EntityNotFoundException {
       Hospital hospital = toHospital(new Hospital(),hospitalDto);
       hospital.setId(id);
        hospitalRepository.updateExisting(hospital);
        return hospital;
    }
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
    private Hospital toHospital(Hospital hospital, HospitalDto hospitalDto) {
        hospital.setAddress(hospitalDto.getAddress());
        hospital.setName(hospitalDto.getName());
        return hospital;
    }

    private Department toDepartment(Department department, CreateDepartmentDto createDepartmentDto) {
        department.setName(createDepartmentDto.getName());
        return department;
    }

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
