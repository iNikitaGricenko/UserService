package com.wolfhack.user.command.model.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "user")
public class UserEntity {

	@Id
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private LocalDate registered = LocalDate.now();
}
