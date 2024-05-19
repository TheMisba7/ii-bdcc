package org.mansar.digitalbanking.service;

import org.mansar.digitalbanking.dto.PageContainer;
import org.mansar.digitalbanking.dto.mapper.IMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class AbstractService <T, E, R extends JpaRepository<E, Long>>{
    protected final R dao;
    protected final IMapper<E, T> mapper;

    protected AbstractService(R dao, IMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    protected PageContainer<T> map(Page<E> page) {
        return new PageContainer<>(
                mapper.toDTO(page.getContent()), page.getNumber(),
                page.getSize(), page.getTotalPages(),
                page.getNumberOfElements());
    }

    public Optional<E> getById(Long id) {
        return dao.findById(id);
    }
}
