package org.mansar.digitalbanking.dto.mapper;

import org.mansar.digitalbanking.dto.CustomerDTO;
import org.mansar.digitalbanking.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper extends IMapper<Customer, CustomerDTO>{
}
