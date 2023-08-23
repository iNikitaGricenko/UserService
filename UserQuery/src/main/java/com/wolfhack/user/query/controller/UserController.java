package com.wolfhack.user.query.controller;

import com.wolfhack.user.query.mapper.UserMapper;
import com.wolfhack.user.query.model.User;
import com.wolfhack.user.query.model.dto.UserResponseDTO;
import com.wolfhack.user.query.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

	@GetMapping
	public List<UserResponseDTO> retrieveAll() {
		return userService.findAll().stream().map(userMapper::toResponse).toList();
	}

	@GetMapping("/{id}")
	public UserResponseDTO retrieveOne(@PathVariable Long id) {
		return userMapper.toResponse(userService.findById(id));
	}

}
