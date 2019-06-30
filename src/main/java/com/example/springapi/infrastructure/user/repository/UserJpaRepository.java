package com.example.springapi.infrastructure.user.repository;

import com.example.springapi.infrastructure.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPAを利用するためのインタフェース
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}
