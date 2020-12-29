package com.springhow.examples.springboot.security.rbac.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    @OneToMany(mappedBy = "user")
    private List<UserToRole> userToRoles;
}
