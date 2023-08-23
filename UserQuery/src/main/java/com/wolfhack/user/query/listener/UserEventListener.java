package com.wolfhack.user.query.listener;

import com.wolfhack.user.query.annotations.Listener;
import com.wolfhack.user.query.mapper.UserMapper;
import com.wolfhack.user.query.model.event.UserEvent;
import com.wolfhack.user.query.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@Listener
@RequiredArgsConstructor
public class UserEventListener {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@KafkaListener(topics = "${event.user.topic}", groupId = "group-id", containerFactory = "topicUserEventContainerFactory")
	public void listenSingleMessage(UserEvent event) {
		handleEvent(event);
	}

	public void handleEvent(UserEvent event) {
		switch (event.getEventType()) {
			case CREATE -> userRepository.save(userMapper.toEntity(event.getData()));
			case UPDATE -> userRepository.findById(event.getId())
					.map(userEntity -> userMapper.partialUpdate(event.getData(), userEntity))
					.ifPresent(userRepository::save);
			case DELETE -> userRepository.deleteById(event.getId());
			default -> {}
		}
	}

}
