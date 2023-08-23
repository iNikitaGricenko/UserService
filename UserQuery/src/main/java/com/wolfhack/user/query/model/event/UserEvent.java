package com.wolfhack.user.query.model.event;

import com.wolfhack.user.query.model.User;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
public class UserEvent implements Serializable {

	private Long id;

	private UUID eventId = UUID.randomUUID();

	private Instant createdAt = Instant.now();

	private EventType eventType = EventType.CREATE;

	private User data;

}
