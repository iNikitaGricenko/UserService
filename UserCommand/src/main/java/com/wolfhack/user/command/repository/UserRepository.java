package com.wolfhack.user.command.repository;

import com.wolfhack.user.command.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Long> {
}