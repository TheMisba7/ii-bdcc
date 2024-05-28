package org.mansar.digitalbanking.dto.mapper;

import org.mansar.digitalbanking.dto.OperationDTO;
import org.mansar.digitalbanking.model.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OperationMapper extends IMapper<Operation, OperationDTO>{
    @Override
    @Mapping(target = "accountId", source = "account.id")
    OperationDTO toDTO(Operation entity);
}
