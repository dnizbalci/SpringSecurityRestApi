package com.works.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rid;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
