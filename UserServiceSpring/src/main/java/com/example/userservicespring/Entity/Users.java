package com.example.userservicespring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //tu tang
    @Column(name = "UserID")
    private int userID;

    @Column(name = "Username", unique = true, nullable = false)
    private String username;
    @Column(name = "Password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "Email")
    private String email;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Role")
    private String role;

    private Set<Roles>  listRoles = new HashSet<>();

}
