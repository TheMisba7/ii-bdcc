package org.mansar.paymentservice.dao;

import org.mansar.paymentservice.model.PaymentType;
import org.mansar.paymentservice.model.Payment;
import org.mansar.paymentservice.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentCode(String code);
    List<Payment> findByStudentMajorId(String majorId);
    List<Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);
}
