package com.example.userservicespring.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Role")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    private String roleId;
    @Column(name = "roleName")
    @Enumerated
    private ERole roleName;

}
