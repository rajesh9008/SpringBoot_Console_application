package com.college.medicare.repository;

import com.college.medicare.domin.Doctor;
import com.college.medicare.domin.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
