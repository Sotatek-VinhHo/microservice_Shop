package com.example.ProductServiceSpring.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String address;
    private String phone;
    private Role role;
    private Double balance;

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
//    }
}

enum Role {
    ADMIN, MEMBER
}
