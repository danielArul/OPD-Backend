package com.daniel.simpleOPD.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="APPOINTMENT")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    private boolean approved;

    private String notes;

    @OneToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;

}
