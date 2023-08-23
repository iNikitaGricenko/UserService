package com.wolfhack.user.command.model.entity;

import com.wolfhack.user.command.model.event.EventType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(value = "user_event")
public class UserEventEntity {

	@Id
	private String eventId;

	private EventType eventType;

	private LocalDate eventData;

	private String entityType;

	private UserEntity payload;

	public UserEventEntity(String eventId, EventType eventType, LocalDate eventData, UserEntity payload) {
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventData = eventData;
		this.entityType = payload.getClass().getSimpleName();
		this.payload = payload;
	}

	public UserEventEntity(EventType eventType, LocalDate eventData, UserEntity payload) {
		this.eventType = eventType;
		this.eventData = eventData;
		this.entityType = payload.getClass().getSimpleName();
		this.payload = payload;
	}

}
