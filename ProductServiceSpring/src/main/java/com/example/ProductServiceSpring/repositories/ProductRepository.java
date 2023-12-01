package com.example.ProductServiceSpring.repositories;

import com.example.ProductServiceSpring.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>, JpaRepository<ProductEntity, Long> {

    @Modifying
    @Query("UPDATE ProductEntity p set p.amount = p.amount + :amount where p.id = :id")
    @Transactional
    void updateAmount(Long id, Long amount);
}
