package com.example.userservicespring.dtos;

import com.example.userservicespring.entities.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO {
    private  long id;
    private String email;
    private String address;
    private String phone;
    private Role role;
    private Double balance;
}
