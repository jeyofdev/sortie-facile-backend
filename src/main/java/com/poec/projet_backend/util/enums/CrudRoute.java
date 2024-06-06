package com.poec.projet_backend.util.enums;

public enum CrudRoute {
    ALL("/all"),
    ID("/{id}"),
    ADD("/add"),
    UPDATE("update/{id}"),
    DELETE("delete/{id}"),
    ;
    CrudRoute (String path){}

    @Override
    public String toString() {
        return "CrudRoute{}";
    }
}
