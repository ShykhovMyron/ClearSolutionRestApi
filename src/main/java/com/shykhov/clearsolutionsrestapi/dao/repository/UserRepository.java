package com.shykhov.clearsolutionsrestapi.dao.repository;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM UserEntity u WHERE u.birthDate BETWEEN :from AND :to")
    List<UserEntity> getUsersTimeInterval(@Param("from") LocalDate fromDate, @Param("to") LocalDate toDate);
}