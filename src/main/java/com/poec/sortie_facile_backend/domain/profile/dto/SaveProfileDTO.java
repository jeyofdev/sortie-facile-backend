package com.poec.sortie_facile_backend.domain.profile.dto;

public record SaveProfileDTO(
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        Long postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth
        /*String cityName,
        String departmentName,
        String regionName*/
) {

}
