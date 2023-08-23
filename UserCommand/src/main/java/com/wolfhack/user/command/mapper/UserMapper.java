package com.wolfhack.user.command.mapper;

import com.wolfhack.user.command.model.User;
import com.wolfhack.user.command.model.dto.UserRequestDto;
import com.wolfhack.user.command.model.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	UserEntity toEntity(User user);

	User toUser(UserEntity userEntity);

	User toUser(UserRequestDto userRequestDto);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(User user, @MappingTarget UserEntity userEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserEntity partialUpdate(UserRequestDto userRequestDto, @MappingTarget UserEntity userEntity);
}