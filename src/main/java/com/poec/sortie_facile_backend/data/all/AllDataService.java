package com.poec.sortie_facile_backend.data.all;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AllDataService {
    public AllDataResponse getAllDatas() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("data.json");

        return objectMapper.readValue(resource.getFile(), AllDataResponse.class);
    }
}
