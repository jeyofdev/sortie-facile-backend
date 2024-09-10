package com.poec.sortie_facile_backend.core.interfaces;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface BaseDomainService<T> {
    public T add(T entity);

    public List<T> findAll();

    public T findById(Long entityId) throws EntityNotFoundException;

    public T updateById(T entity, Long entityId);

    public void deleteById(Long entityId);
}
