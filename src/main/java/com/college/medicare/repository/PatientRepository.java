package com.college.medicare.repository;

import com.college.medicare.domin.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Transactional
    @Modifying
    @Query("delete from Patient p where p.fullName = :fullName")
    void deleteByFullName(String fullName);

    @Transactional
    @Modifying
    @Query(value = "select p from Patient p where p.doctorId = :doctorId")
    List<Patient> findByDocterId(Long doctorId);
}
