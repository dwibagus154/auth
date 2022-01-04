package com.dwibagus.auths.payload;

import lombok.Data;

@Data
public class UsernamePassword {
    private String username;
    private String password;
    private String email;
    private Integer isAdmin;
    private String fullname;
    private String phone_number;
}