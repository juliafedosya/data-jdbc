package com.korabelska.demo.service;

import com.korabelska.demo.dto.DepartmentDto;
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


    public List<Hospital> findAll() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals;
    }

    public Hospital findById(String id) throws EntityNotFoundException {
        Optional<Hospital> hospital = hospitalRepository.findByKey(id);
        return hospital.orElseThrow(() -> new EntityNotFoundException(id));
    }

    public List<Department> findAllDepartmentsByHospitalId(String id) throws EntityNotFoundException {
        Optional<Hospital> optionalHospital = hospitalRepository.findByKey(id);
        return optionalHospital.map(Hospital::getDepartments).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public Department findDepartmentByIdAndHospitalId(String departmentId, String hospitalId) throws EntityNotFoundException {
        Optional<Department> department = departmentRepository.findByKey(hospitalId, departmentId);
        return department.orElseThrow(() -> new EntityNotFoundException(departmentId));
    }

    public Hospital create(HospitalDto hospitalDto) {
        Hospital hospital = this.toHospital(new Hospital(), hospitalDto);
        hospitalRepository.create(hospital);
        return hospital;
    }

    public Hospital createDepartment(DepartmentDto departmentDto, String hospitalId)
            throws EntityNotFoundException {
        Hospital hospital = hospitalRepository.findByKey(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException(hospitalId));
        Department department = toDepartment(new Department(), departmentDto);
        department.setHospitalId(hospitalId);
        hospital.addDepartment(departmentRepository.create(department));
        return hospital;
    }


    public Hospital update(HospitalDto hospitalDto, String id) throws EntityNotFoundException {
        Hospital hospital = toHospital(new Hospital(), hospitalDto);
        hospital.setId(id);
        hospitalRepository.updateExisting(hospital);
        return hospital;
    }

    public Department updateDepartment(DepartmentDto departmentDto, String departmentId, String hospitalId) throws EntityNotFoundException {
        Department department = departmentRepository
                .findByKey(hospitalId, departmentId)
                .orElseThrow(() -> new EntityNotFoundException(departmentId));
        department.setName(departmentDto.getName());
        return departmentRepository.updateExisting(department);
    }

    public void delete(String id) {
        hospitalRepository.deleteByKey(id);
    }

    public void deleteDepartment(String hospitalId, String departmentId) {
        departmentRepository.deleteByKey(hospitalId, departmentId);
    }

    private Hospital toHospital(Hospital hospital, HospitalDto hospitalDto) {
        hospital.setAddress(hospitalDto.getAddress());
        hospital.setName(hospitalDto.getName());
        return hospital;
    }

    private Department toDepartment(Department department, DepartmentDto departmentDto) {
        department.setName(departmentDto.getName());
        return department;
    }

}
