package com.wolfhack.user.query.service;

import com.wolfhack.user.query.mapper.UserMapper;
import com.wolfhack.user.query.model.User;
import com.wolfhack.user.query.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public User findById(Long id) {
		return userRepository.findById(id)
				.map(userMapper::toUser)
				.orElseThrow();
	}

	public List<User> findAll() {
		return userRepository.findAll()
				.stream()
				.map(userMapper::toUser)
				.toList();
	}

}
