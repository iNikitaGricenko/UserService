package com.wolfhack.user.command.model.event;

import com.wolfhack.user.command.model.User;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserUpdate implements UserEvent {

	private final Long id;

	private UUID eventId = UUID.randomUUID();

	private Instant createdAt = Instant.now();

	private EventType eventType = EventType.UPDATE;

	private final User data;

}
