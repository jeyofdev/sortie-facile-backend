package com.poec.sortie_facile_backend.data.all.user;

import lombok.Data;

@Data
public class UserData {
    private String nickname;
    private String email;
    private String password;
    private String role;
}
