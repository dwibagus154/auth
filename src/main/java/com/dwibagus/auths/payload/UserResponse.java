package com.dwibagus.auths.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Integer isAdmin;
    private boolean active;
    private Date created_at = new Date();
    private Date updated_at = new Date();
}
