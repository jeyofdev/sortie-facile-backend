package com.poec.sortie_facile_backend.data.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationCityInfo {
    private String label;
    private String zipCode;
}
