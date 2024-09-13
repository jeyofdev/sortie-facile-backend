package com.poec.sortie_facile_backend.domain.profile.dto;

import java.util.List;

public record ProfileDTO(
        Long id,
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        Long postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth,
        List<Long> activityIds,
        Long regionId,
        Long departmentId,
        Long cityId
        /*List<Long> categoryIds*/
        /*String cityName,
        String departmentName,
        String regionName,*/
        /*List<Long> bookingIds,*/

) {

}
