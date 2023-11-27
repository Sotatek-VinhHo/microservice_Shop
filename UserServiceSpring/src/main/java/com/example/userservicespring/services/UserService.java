package com.example.userservicespring.services;

import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<UserEntity> findAllUsers() {

        return userRepository.findAll();
    }
}
