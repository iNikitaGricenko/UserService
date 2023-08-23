package com.wolfhack.user.command.model.event;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public interface Event<T> extends Serializable {

	UUID getEventId();
	Long getId();
	Instant getCreatedAt();

	EventType getEventType();

	T getData();

}
