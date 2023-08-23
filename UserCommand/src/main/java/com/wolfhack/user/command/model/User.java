package com.wolfhack.user.command.model;

import com.wolfhack.user.command.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Domain model for {@link UserEntity}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate registered;
}