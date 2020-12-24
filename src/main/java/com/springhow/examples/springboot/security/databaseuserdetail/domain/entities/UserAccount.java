package com.springhow.examples.springboot.security.databaseuserdetail.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean active;
}
