package com.poec.sortie_facile_backend.data.all.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poec.sortie_facile_backend.core.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDataInfo {
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")
    private Role role;
}