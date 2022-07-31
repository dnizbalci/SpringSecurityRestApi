package com.works.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;

    @NotBlank(message = "Name can not blank")
    private String firstName;

    @NotBlank(message = "Lastname can not blank")
    private String lastName;

    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    private String password;

    private boolean enabled;
    private boolean tokenExpired;


    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_uid", referencedColumnName = "uid"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_rid", referencedColumnName = "rid"))
    private List<Role> roles;
}
