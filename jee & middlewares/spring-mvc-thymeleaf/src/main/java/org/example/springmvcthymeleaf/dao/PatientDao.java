package org.example.springmvcthymeleaf.dao;

import org.example.springmvcthymeleaf.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {
    Page<Patient> findPatientByNameContainingIgnoreCase(String name, Pageable pageable);
}
