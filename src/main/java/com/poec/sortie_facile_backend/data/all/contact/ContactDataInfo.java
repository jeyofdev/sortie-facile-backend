package com.poec.sortie_facile_backend.data.all.contact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDataInfo {
    private String title;
    private String email;
    private String message;
    private Boolean isRead;
}
