package com.poec.sortie_facile_backend.data.location.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poec.sortie_facile_backend.data.location.LocationData;
import lombok.Data;

import java.util.List;

@Data
public class ListLocationDataResponse {
    @JsonProperty("locations")
    private List<LocationData> locationDataList;
}
