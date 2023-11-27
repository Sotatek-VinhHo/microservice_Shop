package com.example.userservicespring.repositories;

import com.example.userservicespring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1") // tim user thong qua email
    Optional<UserEntity> findByEmail(String email); //lay thong tin cua user thong qua email roi tra ve dang Optional
}
