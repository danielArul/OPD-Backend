package com.daniel.simpleOPD.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="doctor_availability_table")
@Data
public class DoctorAvailabilityTable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Doctor_ID")
    private Doctor doctor;

    @Column(name="Available_times")
    private String availableTimes;
}
