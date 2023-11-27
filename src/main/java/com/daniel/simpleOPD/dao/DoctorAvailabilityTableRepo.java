package com.daniel.simpleOPD.dao;

import com.daniel.simpleOPD.entity.DoctorAvailabilityTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAvailabilityTableRepo extends JpaRepository<DoctorAvailabilityTable,Long> {
    @Query(
            value = "SELECT * FROM doctor_availability_table s where s.Doctor_ID = ?1",
            nativeQuery = true
    )
    List<DoctorAvailabilityTable> findByDoctorID(String id);
}
