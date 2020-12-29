package com.springhow.examples.springboot.security.rbac.domain.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<UserRoleToPrivilege> userRoleToPrivileges;
}

