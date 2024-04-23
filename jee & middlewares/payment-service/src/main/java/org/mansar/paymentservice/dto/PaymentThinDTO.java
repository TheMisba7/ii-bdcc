package org.mansar.paymentservice.dto;

import org.mansar.paymentservice.model.PaymentStatus;
import org.mansar.paymentservice.model.PaymentType;

import java.time.LocalDate;

public record PaymentThinDTO(Long id, LocalDate paidAt, PaymentStatus status, PaymentType type, Double amount) {}
