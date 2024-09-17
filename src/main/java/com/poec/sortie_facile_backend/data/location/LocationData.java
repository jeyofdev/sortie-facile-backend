package com.poec.sortie_facile_backend.data.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LocationData {
    @JsonProperty("insee_code")
    private String inseeCode;

    @JsonProperty("city_code")
    private String cityCode;

    @JsonProperty("zip_code")
    private String zipCode;

    @JsonProperty("label")
    private String label;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("department_number")
    private String departmentNumber;

    @JsonProperty("region_name")
    private String regionName;

    @JsonProperty("region_geojson_name")
    private String regionGeojsonName;
}
