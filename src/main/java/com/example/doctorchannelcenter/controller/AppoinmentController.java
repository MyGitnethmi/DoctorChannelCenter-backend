package com.example.doctorchannelcenter.controller;

import com.example.doctorchannelcenter.exception.ResourceNotFoundException;
import com.example.doctorchannelcenter.model.Appointment;
import com.example.doctorchannelcenter.repository.AppoinmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class AppoinmentController {
    @Autowired
    private AppoinmentRepository appoinmentRepository;

    @GetMapping("/appoinments")
    public List<Appointment> getAllAppoinments(){
        return appoinmentRepository.findAll();
    }

    @PostMapping("/appoinments")
    public Appointment createAppoinments(@RequestBody Appointment Appointment) {
        return appoinmentRepository.save(Appointment);
    }
    @GetMapping("/appoinments/{id}")
    public ResponseEntity<Appointment> getAppoinmentById(@PathVariable Long id) {
        Appointment Appointment = appoinmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appoinment not exist with id :" + id));
        return ResponseEntity.ok(Appointment);
    }
    @PutMapping("/appoinments/{id}")
    public ResponseEntity<Appointment> updateAppoinment(@PathVariable Long id, @RequestBody Appointment appointmentDetails){
        Appointment Appointment = appoinmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appoinment not exist with id :" + id));

        Appointment.setDoctorname(appointmentDetails.getDoctorname());
        Appointment.setPatientname(appointmentDetails.getPatientname());
        Appointment.setPhone(appointmentDetails.getPhone());
        Appointment.setAppoinmentnumber(appointmentDetails.getAppoinmentnumber());
        Appointment.setTime(appointmentDetails.getTime());
        Appointment.setDate(appointmentDetails.getDate());

        Appointment updateAppointment = appoinmentRepository.save(Appointment);
        return ResponseEntity.ok(updateAppointment);
    }
    @DeleteMapping("/appoinments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAppoinment(@PathVariable Long id){
        Appointment Appointment = appoinmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("appoinments not exist with id :" + id));

        appoinmentRepository.delete(Appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
