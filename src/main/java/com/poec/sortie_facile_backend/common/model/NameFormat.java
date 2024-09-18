package com.poec.sortie_facile_backend.common.model;

import com.poec.sortie_facile_backend.util.Helper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameFormat {
    private String firstname;
    private String lastname;
    private String fullName;

    public NameFormat(String firstname, String lastname) {
        this.firstname = Helper.capitalizeFirstLetter(firstname);
        this.lastname = Helper.capitalizeFirstLetter(lastname);
        this.fullName = this.firstname + " " + this.lastname;
    }
}
