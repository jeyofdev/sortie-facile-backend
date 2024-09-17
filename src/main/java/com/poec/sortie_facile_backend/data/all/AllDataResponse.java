package com.poec.sortie_facile_backend.data.all;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poec.sortie_facile_backend.data.all.activity.ActivityData;
import com.poec.sortie_facile_backend.data.all.category.CategoryData;
import com.poec.sortie_facile_backend.data.all.contact.ContactData;
import com.poec.sortie_facile_backend.data.all.profile.ProfileData;
import lombok.Data;

import java.util.List;

@Data
public class AllDataResponse {
    @JsonProperty("contacts")
    private List<ContactData> contactDataList;

    @JsonProperty("categories")
    private List<CategoryData> categoryDataList;

    @JsonProperty("profiles")
    private List<ProfileData> profileDataList;

    @JsonProperty("activities")
    private List<ActivityData> activityDataList;
}
