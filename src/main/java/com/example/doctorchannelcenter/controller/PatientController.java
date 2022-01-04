package com.example.doctorchannelcenter.controller;

import com.example.doctorchannelcenter.exception.ResourceNotFoundException;
import com.example.doctorchannelcenter.model.Patient;
import com.example.doctorchannelcenter.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    // get all employees
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        System.out.println(patient);
        return patientRepository.save(patient);
    }

    // get employee by id rest api
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));
        return ResponseEntity.ok(patient);
    }

    // update employee rest api

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id :" + id));

        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setEmailId(patientDetails.getEmailId());
        patient.setPhone(patientDetails.getPhone());

        Patient updatePatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatePatient);
    }

    // delete employee rest api
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("patient not exist with id :" + id));
        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
