package com.wolfhack.user.command.repository;

import com.wolfhack.user.command.model.entity.UserEventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEventRepository extends MongoRepository<UserEventEntity, String> {
}