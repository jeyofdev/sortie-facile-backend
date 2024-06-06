package com.poec.projet_backend.util.enums;

public enum DomainRoute {

    BASE_URL("/api/v1"),
    ACTIVITY("/activity"),
    BOOKING("/booking"),
    CATEGORY("/category"),
    CITY("/city"),
    CONTACT("/contact"),
    DEPARTMENT("/department"),
    PROFILE("/profile"),
    REGION("/region")
    ;

    DomainRoute(String path) {
    }
}
