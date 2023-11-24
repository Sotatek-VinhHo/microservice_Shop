package com.example.userservicespring.repositories;

import com.example.userservicespring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
//    tim nguoi dung qua email
    Optional<UserEntity> findByEmail(String email);
}
