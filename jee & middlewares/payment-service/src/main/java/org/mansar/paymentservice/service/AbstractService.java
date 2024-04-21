package org.mansar.paymentservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService <E, R extends JpaRepository<E, Long>>{
    private final R dao;

    protected AbstractService(R dao) {
        this.dao = dao;
    }

    protected E save(E entity) {
        return dao.save(entity);
    }

    public E getById(Long id) {
        return dao.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<E> findAll() {
        return dao.findAll();
    }
    protected void delete(Long id) {
        dao.deleteById(id);
    }
}
