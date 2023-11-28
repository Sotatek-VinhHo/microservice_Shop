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

    public void updateUserProfile(Long id, String address, String phone) {
            UserEntity foundUserEntity = findUserById(id);
            if (phone != null) {
                foundUserEntity.setPhone(phone);
            }
            if (address != null) {
                foundUserEntity.setAddress(address);
            }
            userRepository.save(foundUserEntity);
        }

    public void updateUserBalance(Long id, Double balance) {
        UserEntity foundUserEntity = findUserById(id);
        foundUserEntity.setBalance(balance);
        userRepository.save(foundUserEntity);
    }
}

