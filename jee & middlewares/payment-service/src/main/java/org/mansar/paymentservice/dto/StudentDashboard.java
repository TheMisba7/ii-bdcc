package org.mansar.paymentservice.dto;

import org.mansar.paymentservice.model.Student;

import java.util.List;

public record StudentDashboard(Student student, List<PaymentThinDTO> payments, double totalPayment) {}
