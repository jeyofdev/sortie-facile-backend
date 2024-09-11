package com.poec.sortie_facile_backend.core.interfaces;

public interface BaseDomainMapper<T, D, S> {
    public D mapFromEntity(T entity);

    public T mapToEntity(S saveEntityDTO);
}
