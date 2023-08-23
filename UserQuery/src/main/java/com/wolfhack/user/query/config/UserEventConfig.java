package com.wolfhack.user.query.config;

import com.wolfhack.user.query.model.event.UserEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class UserEventConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserEvent> topicUserEventContainerFactory(
			ConsumerFactory<String, UserEvent> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, UserEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

}
