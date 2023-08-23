package com.wolfhack.user.command.publisher;

import com.wolfhack.user.command.annotations.Publisher;
import com.wolfhack.user.command.model.event.UserEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

@Publisher
public class UserPublisherImpl implements UserPublisher {

	private final KafkaTemplate<String, UserEvent> kafkaTemplate;
	private final String userEventTopic;

	public UserPublisherImpl(KafkaTemplate<String, UserEvent> kafkaTemplate, @Value("${event.user.topic}") String userEventTopic) {
		this.kafkaTemplate = kafkaTemplate;
		this.userEventTopic = userEventTopic;
	}

	@Override
	public void publish(UserEvent event) {
		Message<UserEvent> eventMessage = MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, userEventTopic)
				.build();

		kafkaTemplate.send(eventMessage);
	}

	@Override
	public void publish(List<UserEvent> event) {
		Message<List<UserEvent>> eventMessage = MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, userEventTopic)
				.build();

		kafkaTemplate.send(eventMessage);
	}

	@Override
	public void fail(UserEvent event) {
		Message<UserEvent> eventMessage = MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, userEventTopic)
				.build();

		kafkaTemplate.send(eventMessage);
	}

	@Override
	public void fail(List<UserEvent> event) {
		Message<List<UserEvent>> eventMessage = MessageBuilder.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, userEventTopic)
				.build();

		kafkaTemplate.send(eventMessage);
	}

}
