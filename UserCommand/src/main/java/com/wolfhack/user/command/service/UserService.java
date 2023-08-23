package com.wolfhack.user.command.service;

import com.wolfhack.user.command.mapper.UserMapper;
import com.wolfhack.user.command.model.User;
import com.wolfhack.user.command.model.entity.DatabaseSequence;
import com.wolfhack.user.command.model.entity.UserEntity;
import com.wolfhack.user.command.model.entity.UserEventEntity;
import com.wolfhack.user.command.model.event.UserCreate;
import com.wolfhack.user.command.model.event.UserDelete;
import com.wolfhack.user.command.model.event.UserUpdate;
import com.wolfhack.user.command.publisher.UserPublisher;
import com.wolfhack.user.command.repository.UserEventRepository;
import com.wolfhack.user.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService extends AbstractMongoEventListener<UserEntity> {

	private final UserPublisher userPublisher;
	private final UserRepository userRepository;
	private final UserEventRepository userEventRepository;
	private final UserMapper userMapper;
	private final DatabaseSequenceService databaseSequenceService;

	@Override
	public void onBeforeConvert(BeforeConvertEvent<UserEntity> event) {
		if (event.getSource().getId() < 1) {
			event.getSource().setId(databaseSequenceService.generateSequence(DatabaseSequence.SEQUENCE_NAME));
		}
	}

	public Long save(User user) {
		UserEntity entity = userMapper.toEntity(user);
		entity.setId(databaseSequenceService.generateSequence(DatabaseSequence.SEQUENCE_NAME));
		UserEntity saved = userRepository.save(entity);
		Long id = saved.getId();

		user.setId(id);
		user.setRegistered(LocalDate.now());

		UserCreate event = new UserCreate(id, user);
		userPublisher.publish(event);

		UserEventEntity userEventEntity = new UserEventEntity(event.getEventType(), LocalDate.now(), saved);
		userEventRepository.save(userEventEntity);

		return id;
	}

	public void delete(Long id) {
		UserEntity userEntity = userRepository.findById(id).orElseThrow();
		User user = userMapper.toUser(userEntity);

		UserDelete event = new UserDelete(id);
		userPublisher.publish(event);

		UserEventEntity userEventEntity = new UserEventEntity(event.getEventType(), LocalDate.now(), userEntity);
		userEventRepository.save(userEventEntity);
	}

	public Long update(Long id, User user) {
		UserEntity updated = userRepository.findById(id)
				.map(userEntity -> userMapper.partialUpdate(user, userEntity))
				.map(userRepository::save)
				.orElseThrow();

		UserUpdate event = new UserUpdate(id, userMapper.toUser(updated));
		userPublisher.publish(event);

		UserEventEntity userEventEntity = new UserEventEntity(event.getEventType(), LocalDate.now(), updated);
		userEventRepository.save(userEventEntity);

		return id;
	}

}
