package com.antss_prescription.repository;

import com.antss_prescription.entity.Doctor;
import com.antss_prescription.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByUser(User user);
    long countByUserAndActiveTrue(User user);
}
