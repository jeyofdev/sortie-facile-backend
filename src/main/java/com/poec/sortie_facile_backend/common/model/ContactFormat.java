package com.poec.sortie_facile_backend.common.model;

import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.util.Helper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactFormat {
    private String phone;
    private SocialFormat socialMedia;

    public ContactFormat(String phone, SocialFormat socialMedia) {
        this.phone = Helper.formatPhoneNumber(phone);
        this.socialMedia = socialMedia;
    }
}
