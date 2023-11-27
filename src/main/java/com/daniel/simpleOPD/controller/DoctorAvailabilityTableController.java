package com.daniel.simpleOPD.controller;

import com.daniel.simpleOPD.dao.DoctorAvailabilityTableRepo;
import com.daniel.simpleOPD.dao.DoctorRepo;
import com.daniel.simpleOPD.entity.DoctorAvailabilityTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorAvailabilityTableController {

    @Autowired
    private DoctorAvailabilityTableRepo doctorAvailabilityTableRepo;

    @Autowired
    private DoctorRepo doctorRepo;

//    @PostMapping("/addDoctorAvailability")
//    public DoctorAvailabilityTable addDoctorAvailability(
//            @RequestParam("selectedDoctor") Doctor selectedDoctor,
//            @RequestParam("selectedDateTime") String selectedDateTime){
//
//        System.out.println(selectedDoctor);
//        System.out.println(selectedDateTime);
////        DoctorAvailabilityTable table=new DoctorAvailabilityTable();
////        table.setDoctor(selectedDoctor);
////        table.setAvailableTimes(selectedDateTime);
////        DoctorAvailabilityTable result = doctorAvailabilityTableRepo.save(table);
////        return result;
//        return null;
//    }

    @PostMapping("/addDoctorAvailability")
    public DoctorAvailabilityTable addDoctorAvailability(@RequestBody DoctorAvailabilityTable doctorAvailabilityTable){

        System.out.println(doctorAvailabilityTable);
        DoctorAvailabilityTable obj = new DoctorAvailabilityTable();
        obj.setDoctor(doctorAvailabilityTable.getDoctor());
        obj.setAvailableTimes(doctorAvailabilityTable.getAvailableTimes());

        DoctorAvailabilityTable savedObj = doctorAvailabilityTableRepo.save(obj);

        return savedObj;

    }

    @GetMapping("/getDoctorAvailability/{id}")
    public ResponseEntity<List<DoctorAvailabilityTable>> getDoctorAvailability(@PathVariable String id){

        List<DoctorAvailabilityTable> results= doctorAvailabilityTableRepo.findByDoctorID(id);
        System.out.println(results);
        return new ResponseEntity<>(results, HttpStatus.OK);

    }
}
