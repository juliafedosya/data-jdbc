package com.korabelska.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PatientService {

//    PatientRepository patientRepository;
//
//    DoctorRepository doctorRepository;
//
//    @Autowired
//    public PatientService(PatientRepository patientRepository,DoctorRepository doctorRepository) {
//        this.patientRepository = patientRepository;
//        this.doctorRepository = doctorRepository;
//    }
//
//    public Iterable<Patient> findAll() {
//        Iterable<Patient> patients = patientRepository.findAll();
//        return patients;
//    }
//
//    public Optional<Patient> findById(Long id) {
//        Optional<Patient> patient= patientRepository.findById(id);
//        return patient;
//    }
//
//    public List<PatientDiagnosis> findPatientDiagnosesByPatientId(Long id) {
//        List<PatientDiagnosis> patientDiagnoses = patientRepository.findPatientDiagnosesByPatientId(id);
//        return patientDiagnoses;
//    }
//
//    public Optional<PatientDiagnosis> findPatientDiagnosisByIdAndPatientId(Long patientId,Long patientDiagnosisId) {
//        Optional<PatientDiagnosis> patientDiagnoses = patientRepository
//                .findPatientDiagnosisByIdAndByPatientId(patientDiagnosisId,patientId);
//        return patientDiagnoses;
//    }
//
//    public Patient create(PatientDto patientDto) {
//        Patient patient = this.toPatient(patientDto);
//        patientRepository.save(patient);
//        return patient;
//    }
//
//    public Patient createDiagnosis(DiagnosisDto diagnosisDto, Patient patient) {
//        PatientDiagnosis diagnosis = new PatientDiagnosis();
//
//        diagnosis = this.toPatientDiagnosis(diagnosis,diagnosisDto);
//
//        patient.addPatientDiagnosis(diagnosis);
//        patientRepository.save(patient);
//        return patient;
//    }
//
//    public Patient update(Patient current,PatientDto patientDto){
//        current.setFirstName(patientDto.getFirstName());
//        current.setDateOfBirth(patientDto.getDateOfBirth());
//        patientRepository.save(current);
//        return current;
//    }
//
//    public Patient updateDiagnosis(DiagnosisDto diagnosisDto, PatientDiagnosis diagnosis,Patient patient) {
//        patient.getPatientDiagnoses().remove(diagnosis);
//        diagnosis = this.toPatientDiagnosis(diagnosis,diagnosisDto);
//        patient.getPatientDiagnoses().add(diagnosis);
//        return patientRepository.save(patient);
//    }
//
//    public void delete(Long id) {
//        Boolean exists = patientRepository.existsById(id);
//        if (exists) {
//            patientRepository.deleteById(id);
//        }
//    }
//
//    public void deleteDiagnosis(Patient patient, Long diagnosisId) {
//
//        Optional<PatientDiagnosis> matchingDiagnosis = patient.getPatientDiagnoses().stream().
//                filter(pd -> pd.getId().equals(diagnosisId)).
//                findFirst();
//
//        if(matchingDiagnosis.isPresent()) {
//            patient.getPatientDiagnoses().remove(matchingDiagnosis.get());
//            patientRepository.save(patient);
//        }
//
//    }
//
//    private Patient toPatient(PatientDto patientDto) {
//        Patient patient = new Patient();
//        patient.setDateOfBirth(patientDto.getDateOfBirth());
//        patient.setFirstName(patientDto.getFirstName());
//        return patient;
//    }
//
//    private PatientDiagnosis toPatientDiagnosis(PatientDiagnosis diagnosis, DiagnosisDto diagnosisDto) {
//        diagnosis.setDateConfirmed(diagnosisDto.getDateConfirmed());
//        diagnosis.setDetails(diagnosisDto.getDetails());
//        diagnosis.setRemarks(diagnosisDto.getRemarks());
//        return diagnosis;
//    }

}
