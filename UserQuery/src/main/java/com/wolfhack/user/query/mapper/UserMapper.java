package com.wolfhack.user.query.mapper;

import com.wolfhack.user.query.model.User;
import com.wolfhack.user.query.model.dto.UserResponseDTO;
import com.wolfhack.user.query.model.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	UserEntity toEntity(User user);

	User toUser(UserEntity userEntity);

	UserResponseDTO toResponse(User user);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(User user, @MappingTarget UserEntity userEntity);

}