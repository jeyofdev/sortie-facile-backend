package com.poec.sortie_facile_backend.data.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poec.sortie_facile_backend.data.contact.model.ListContactDataResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContactDataService {
    public ListContactDataResponse getAllDatas() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("data.json");

        return objectMapper.readValue(resource.getFile(), ListContactDataResponse.class);
    }
}
