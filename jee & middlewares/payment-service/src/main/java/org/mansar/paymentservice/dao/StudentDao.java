package org.mansar.paymentservice.dao;

import org.mansar.paymentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    Student findByCode(String code);
    List<Student> findByMajorId(String majorId);

}
