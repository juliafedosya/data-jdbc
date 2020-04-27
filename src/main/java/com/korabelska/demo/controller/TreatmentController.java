package com.korabelska.demo.controller;


import com.korabelska.demo.service.DoctorService;
import com.korabelska.demo.service.PatientService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentController {

    PatientService patientService;

    DoctorService doctorService;

}
