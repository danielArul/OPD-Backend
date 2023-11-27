package com.daniel.simpleOPD.dao;

import com.daniel.simpleOPD.entity.Appointment;
import com.daniel.simpleOPD.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    //Appointment findByPatient(String name);
    Appointment findByPatient(Patient patient);
    Appointment findByPatientId(Long patientId);
}
