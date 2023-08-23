package com.wolfhack.user.query.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wolfhack.user.query.model.entity.UserEntity;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link UserEntity}
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO implements Serializable {
	String firstName;
	String lastName;
	String email;
	LocalDate registered;
}