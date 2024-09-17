package com.poec.sortie_facile_backend.data.all.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryData {
    @JsonProperty("title")
    private String title;

    @JsonProperty("imgUrl")
    private String imgUrl;
}
