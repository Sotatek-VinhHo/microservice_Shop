package com.example.userservicespring.repositories;

import com.example.userservicespring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1") // tim user thong qua email
    Optional<UserEntity> findByEmail(String email); //lay thong tin cua user thong qua email roi tra ve dang Optional

    @Modifying //capnhat
    @Transactional //thuc hien trong 1 giao dich, neu khong co giao dich thi tao moi
    @Query("UPDATE UserEntity u SET u.balance = u.balance + :balance WHERE u.id = :id")
    void updateBalance(Long id, Double balance);
}
