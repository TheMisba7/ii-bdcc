package org.example.springmvcthymeleaf.app;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractApp <R extends JpaRepository<E, Long>, E> {
    protected final R repository;

    protected AbstractApp(R repository) {
        this.repository = repository;
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public Page<E> getPage(final int page, final int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public void add(final E entity) {
        repository.save(entity);
    }

    public E get(final long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(final long id) {
        repository.deleteById(id);
    }

}

