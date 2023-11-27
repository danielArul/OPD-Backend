package com.daniel.simpleOPD.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="DOCTOR")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="FIRST_NAME")
    private String first_name;

    @Column(name="LAST_NAME")
    private String last_name;

    @Column(name="EMAIL")
    private String email;
}
