package com.poec.sortie_facile_backend.common.model;

import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationFormat {
    private RegionDTO region;
    private DepartmentDTO department;
    private CityDTO city;
}
