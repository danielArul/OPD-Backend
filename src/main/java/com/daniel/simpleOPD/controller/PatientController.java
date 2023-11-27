package com.daniel.simpleOPD.controller;

import com.daniel.simpleOPD.dao.AppointmentRepo;
import com.daniel.simpleOPD.dao.PatientRepo;
import com.daniel.simpleOPD.dto.AppointmentRequest;
import com.daniel.simpleOPD.dto.CompleteAppointment;
import com.daniel.simpleOPD.entity.Appointment;
import com.daniel.simpleOPD.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @GetMapping("/getAllPatients")
    public ResponseEntity<List<Patient>> getAllPatientDetails(){
        try{
            List<Patient> patientList = new ArrayList<>();
            patientRepo.findAll().forEach(patientList::add);

            if(patientList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(patientList,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllPatientsByPage")
    public void getAllPatientDetails2(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size){
        Pageable firstPagewithThreeRecords =
                 PageRequest.of(page,size) ;

        List<Patient> patients = patientRepo.findAll(firstPagewithThreeRecords).getContent();
        long totalElements = patientRepo.findAll(firstPagewithThreeRecords).getTotalElements();
        long totalPages = patientRepo.findAll(firstPagewithThreeRecords).getTotalPages();

        System.out.println();
        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("Patients = " + patients);

    }

    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Optional<Patient> patientData = patientRepo.findById(id);

        if(patientData.isPresent()){
            return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/completeReservation/{PatientId}")
    public void completeReservation(@PathVariable Long PatientId,@RequestBody CompleteAppointment completeAppointment){
        Appointment result= appointmentRepo.findByPatientId(PatientId);

        if (completeAppointment != null) {
            System.out.println("Notes: " + completeAppointment.getNotes());
            System.out.println("Approval: " + completeAppointment.isApproval());
        } else {
            System.out.println("CompleteAppointment is null");
        }

        result.setApproved(completeAppointment.isApproval());
        result.setNotes(completeAppointment.getNotes());

        Appointment obj=appointmentRepo.save(result);


    }

    @PostMapping("/addPatient")
    public ResponseEntity<Patient> addPatient(@RequestBody AppointmentRequest appointmentRequest){

//        Patient patientObj= patientRepo.save(patient);
//
//        return new ResponseEntity<>(patientObj, HttpStatus.OK);
        Patient patientObj= new Patient();
        patientObj.setEmail(appointmentRequest.getEmail());
        patientObj.setFirst_name(appointmentRequest.getFirst_name());
        patientObj.setLast_name(appointmentRequest.getLast_name());
        Patient savedPatient= patientRepo.save(patientObj);

        Appointment appointmentObj=new Appointment();
        appointmentObj.setPatient(savedPatient);
        appointmentObj.setDoctor(appointmentRequest.getSelectedDoctor());
        Appointment savedAppointment=appointmentRepo.save(appointmentObj);
        System.out.println(appointmentRequest);
        return null;
    }

    @PostMapping("/updatePatientById/{id}")
    public ResponseEntity<Patient> updatePatientById(@PathVariable Long id, @RequestBody Patient newPatientData){

        Optional<Patient> oldPatientData = patientRepo.findById(id);

        if(oldPatientData.isPresent()){
            Patient updatedPatientData = oldPatientData.get();
            updatedPatientData.setFirst_name(newPatientData.getFirst_name());
            updatedPatientData.setLast_name(newPatientData.getLast_name());
            updatedPatientData.setDate_of_appointment(newPatientData.getDate_of_appointment());
            updatedPatientData.setEmail(newPatientData.getEmail());

            Patient patientObj = patientRepo.save(updatedPatientData);
            return new ResponseEntity<>(patientObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletePatientById/{id}")
    public ResponseEntity<HttpStatus> deletePatientById(@PathVariable Long id){
        patientRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
