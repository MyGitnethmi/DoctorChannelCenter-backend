package com.example.doctorchannelcenter.repository;

import com.example.doctorchannelcenter.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appointment,Long> {
}
