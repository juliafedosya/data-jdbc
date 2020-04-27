package com.korabelska.demo.service;

import com.korabelska.demo.dto.CreateDepartmentDto;
import com.korabelska.demo.dto.HospitalDto;
import com.korabelska.demo.dto.UpdateDepartmentDto;
import com.korabelska.demo.model.Department;
import com.korabelska.demo.model.Doctor;
import com.korabelska.demo.model.Hospital;
import com.korabelska.demo.model.PatientDiagnosis;
import com.korabelska.demo.repository.DoctorRepository;
import com.korabelska.demo.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HospitalService {

    private HospitalRepository hospitalRepository;

    private DoctorRepository doctorRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository, DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Hospital> findAll() {
        Iterable<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals;
    }

    public Optional<Hospital> findById(Long id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        return hospital;
    }

    public List<Department> findAllDepartmentsByHospitalId(Long id) {
        List<Department> departments = hospitalRepository.findDepartmentsByHospitalId(id);
        return departments;
    }

    public Optional<Department> findDepartmentByIdAndHospitalId(Long hospitalId, Long departmentId) {
        Optional<Department> department = hospitalRepository
                .findDepartmentByIdAndHospitalId(hospitalId,departmentId);
        return department;
    }

    public Hospital create(HospitalDto hospitalDto) {
        Hospital hospital = this.toHospital(new Hospital(),hospitalDto);
        hospitalRepository.save(hospital);
        return hospital;
    }

    public Hospital createDepartment(CreateDepartmentDto createDepartmentDto, Hospital hospital) {
        Department department = toDepartment(new Department(), createDepartmentDto);
        hospital.addDepartment(department);
        hospitalRepository.save(hospital);
        return hospital;
    }

    public Hospital update(Hospital hospital,HospitalDto hospitalDto) {
        hospital = toHospital(hospital,hospitalDto);
        hospitalRepository.save(hospital);
        return hospital;
    }

    public Hospital updateDepartment(UpdateDepartmentDto departmentDto,Department department,Hospital hospital) {
        hospital.getDepartments().remove(department);
        department = toDepartment(department,departmentDto);
        hospital.getDepartments().add(department);
        hospitalRepository.save(hospital);
        return hospital;
    }

    public void delete(Long hospitalId) {
        if(hospitalRepository.existsById(hospitalId)) {
            hospitalRepository.deleteById(hospitalId);
        }
    }

    public void deleteDepartment(Hospital hospital,Long departmentId) {
        Optional<Department> matchingDepartment = hospital.getDepartments().stream().
                filter(pd -> pd.getId().equals(departmentId)).
                findFirst();

        if(matchingDepartment.isPresent()) {
            hospital.getDepartments().remove(matchingDepartment.get());
            hospitalRepository.save(hospital);
        }
    }

    private Hospital toHospital(Hospital hospital, HospitalDto hospitalDto) {
        hospital.setAddress(hospitalDto.getAddress());
        hospital.setName(hospitalDto.getName());
        return hospital;
    }

    private Department toDepartment(Department department, CreateDepartmentDto createDepartmentDto) {
        department.setName(createDepartmentDto.getName());
        return department;
    }

    private Department toDepartment(Department department, UpdateDepartmentDto departmentDto) {
        department.setName(departmentDto.getName());
        Set<Long> doctorIds = departmentDto.getDoctorIds();

        if(!doctorIds.isEmpty()) {
            for(Long doctorId : doctorIds) {
                Optional<Doctor> optDoctor = doctorRepository.findById(doctorId);
                if(optDoctor.isPresent()) {
                    department.addDoctor(optDoctor.get());
                    doctorRepository.changeDepartment(department.getId(), optDoctor.get().getId());
                }
            }
        }
        return department;
    }




}
