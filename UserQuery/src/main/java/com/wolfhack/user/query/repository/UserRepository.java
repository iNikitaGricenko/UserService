package com.wolfhack.user.query.repository;

import com.wolfhack.user.query.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}