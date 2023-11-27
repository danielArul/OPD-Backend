package com.daniel.simpleOPD.controller;

import com.daniel.simpleOPD.dao.DoctorRepo;
import com.daniel.simpleOPD.entity.Doctor;
import com.daniel.simpleOPD.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {
    @Autowired
    private DoctorRepo doctorRepo;

    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<Doctor>> getAllDoctorDetails(){
        try{
            List<Doctor> doctorList = new ArrayList<>();
            doctorRepo.findAll().forEach(doctorList::add);

            if(doctorList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctorList,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getDoctorById/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id){
        Optional<Doctor> doctorData = doctorRepo.findById(id);

        if(doctorData.isPresent()){
            return new ResponseEntity<>(doctorData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        Doctor doctorObj= doctorRepo.save(doctor);
        return new ResponseEntity<>(doctorObj, HttpStatus.OK);
    }

    @PostMapping("/updateDoctorById/{id}")
    public ResponseEntity<Doctor> updateDoctorById(@PathVariable Long id, @RequestBody Patient newDoctorData){

        Optional<Doctor> oldDoctorData = doctorRepo.findById(id);

        if(oldDoctorData.isPresent()){
            Doctor updatedDoctorData = oldDoctorData.get();
            updatedDoctorData.setFirst_name(newDoctorData.getFirst_name());
            updatedDoctorData.setLast_name(newDoctorData.getLast_name());
            updatedDoctorData.setEmail(newDoctorData.getEmail());

            Doctor doctorObj = doctorRepo.save(updatedDoctorData);
            return new ResponseEntity<>(doctorObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteDoctorById/{id}")
    public ResponseEntity<HttpStatus> deleteDoctorById(@PathVariable Long id){
        doctorRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
