package com.example.userservicespring.dtos;

import com.example.userservicespring.entities.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String address;
    private String phone;
    private String password;
    private Role role;
    private Double balance;
}

