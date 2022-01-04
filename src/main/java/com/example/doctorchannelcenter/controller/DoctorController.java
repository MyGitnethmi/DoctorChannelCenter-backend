package com.example.doctorchannelcenter.controller;


import com.example.doctorchannelcenter.exception.ResourceNotFoundException;
import com.example.doctorchannelcenter.model.Doctor;
import com.example.doctorchannelcenter.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }
    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id :" + id));
        return ResponseEntity.ok(doctor);
    }
    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id :" + id));

        doctor.setName(doctorDetails.getName());
        doctor.setDescription(doctorDetails.getDescription());
        doctor.setEmailId(doctorDetails.getEmailId());
        doctor.setPhone(doctorDetails.getPhone());

        Doctor updateDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updateDoctor);
    }
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not exist with id :" + id));

        doctorRepository.delete(doctor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
