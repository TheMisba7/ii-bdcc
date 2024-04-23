package org.mansar.paymentservice.dto.mapper;

import org.mansar.paymentservice.dto.PaymentThinDTO;
import org.mansar.paymentservice.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {
    PaymentThinDTO toThinDTO(Payment payment);
    List<PaymentThinDTO> toThinDTO(List<Payment> payments);
}
