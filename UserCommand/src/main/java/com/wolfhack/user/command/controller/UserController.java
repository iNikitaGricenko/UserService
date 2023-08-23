package com.wolfhack.user.command.controller;

import com.wolfhack.user.command.mapper.UserMapper;
import com.wolfhack.user.command.model.User;
import com.wolfhack.user.command.model.dto.UserRequestDto;
import com.wolfhack.user.command.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

	@PostMapping
	public Long create(@RequestBody UserRequestDto userRequestDto) {
		User user = userMapper.toUser(userRequestDto);
		return userService.save(user);
	}

	@DeleteMapping("/{id}")
	public void create(@PathVariable Long id) {
		userService.delete(id);
	}

	@PatchMapping("/{id}")
	public Long update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
		User user = userMapper.toUser(userRequestDto);
		return userService.update(id, user);
	}

}
