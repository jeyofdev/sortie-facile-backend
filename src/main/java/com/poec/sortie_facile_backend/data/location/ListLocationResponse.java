package com.poec.sortie_facile_backend.data.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListLocationResponse {
    @JsonProperty("locations")
    private List<Location> locations;
}
