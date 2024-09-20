package com.poec.sortie_facile_backend.domain.profile.dto;

import com.poec.sortie_facile_backend.common.model.AddressFormat;
import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;
import com.poec.sortie_facile_backend.common.model.NameFormat;
import com.poec.sortie_facile_backend.common.model.YearFormat;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.category.dto.CategoryDTO;

public record ProfileDTO(
        Long id,
        String email,
        String nickname,
        NameFormat name,
        YearFormat year,
        String phone,
        AddressFormat address,
        String description,
        String avatar,
        ListRelationWithSizeFormat<ActivityDTO> activities,
        ListRelationWithSizeFormat<Long> bookings,
        ListRelationWithSizeFormat<CategoryDTO> categories
) {

}
