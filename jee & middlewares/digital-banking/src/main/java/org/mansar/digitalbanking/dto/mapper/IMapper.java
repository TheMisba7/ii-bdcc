package org.mansar.digitalbanking.dto.mapper;

import java.util.List;

public interface IMapper<E, T> {
    T toDTO(E entity);
    E fromDTO(T dto);

    List<T> toDTO(List<E> dto);
}
