package com.example.userservicespring.dtos;

import lombok.Builder;

@Builder
public record TokenSigninRequestDTO (String email, String password){
}
