package com.dwibagus.auths.model;

import com.sun.istack.NotNull;
import lombok.Data;


import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "members")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Integer isAdmin = 0;
    private String fullname = null;
    private String phone_number = null;
    private Date created_at = new Date();
    private Date updated_at = new Date();
    private boolean active = false;

}