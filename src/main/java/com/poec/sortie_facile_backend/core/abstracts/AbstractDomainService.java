package com.poec.sortie_facile_backend.core.abstracts;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainService;
import com.poec.sortie_facile_backend.util.Helper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractDomainService<T> implements BaseDomainService<T> {
    protected final JpaRepository<T, Long> repository;
    private final String entityName;

    @Autowired
    public AbstractDomainService(JpaRepository<T, Long> repository, String entityName) {
        this.entityName = entityName;
        this.repository = repository;
    }

    @Override
    public T add(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long entityId) throws EntityNotFoundException {
        return repository.findById(entityId).orElseThrow(
                () -> new EntityNotFoundException(Helper.capitalizeFirstLetter(entityName) + " with id " + entityId + " cannot be found"));
    }

    @Override
    public abstract T updateById(T entity, Long entityId);

    @Override
    public void deleteById(Long entityId) {
        findById(entityId);
        repository.deleteById(entityId);
    }
}
