package com.poec.sortie_facile_backend.data.all.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactData {
    @JsonProperty("title")
    private String title;

    @JsonProperty("email")
    private String email;

    @JsonProperty("message")
    private String message;

    @JsonProperty("isRead")
    private Boolean isRead;
}
