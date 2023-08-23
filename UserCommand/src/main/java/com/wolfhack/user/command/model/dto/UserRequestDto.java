package com.wolfhack.user.command.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.user.command.model.entity.UserEntity}
 */
public record UserRequestDto(String firstName, String lastName, String email) implements Serializable {
}