package com.shykhov.clearsolutionsrestapi.dao.repository;

import com.shykhov.clearsolutionsrestapi.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}