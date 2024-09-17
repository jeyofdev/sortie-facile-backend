package com.poec.sortie_facile_backend.data.contact.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poec.sortie_facile_backend.data.contact.ContactData;
import lombok.Data;

import java.util.List;

@Data
public class ListContactDataResponse {
    @JsonProperty("contacts")
    private List<ContactData> contactDataList;
}
