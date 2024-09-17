package com.poec.sortie_facile_backend.data.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poec.sortie_facile_backend.data.location.model.ListLocationDataResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationDataService {
    public ListLocationDataResponse getAllDatas() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("location.data.json");

        return objectMapper.readValue(resource.getFile(), ListLocationDataResponse.class);
    }
}
