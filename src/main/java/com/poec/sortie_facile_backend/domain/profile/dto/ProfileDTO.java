package com.poec.sortie_facile_backend.domain.profile.dto;

import com.poec.sortie_facile_backend.common.model.*;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.category.dto.CategoryDTO;

public record ProfileDTO(
        Long id,
        String email,
        String nickname,
        NameFormat name,
        YearFormat year,
        AddressFormat address,
        ContactFormat contact,
        String description,
        String avatar,
        ListRelationWithSizeFormat<ActivityDTO> activities,
        ListRelationWithSizeFormat<Long> bookings,
        ListRelationWithSizeFormat<CategoryDTO> categories
) {

}
