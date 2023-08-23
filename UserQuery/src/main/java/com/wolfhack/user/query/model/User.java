package com.wolfhack.user.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

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