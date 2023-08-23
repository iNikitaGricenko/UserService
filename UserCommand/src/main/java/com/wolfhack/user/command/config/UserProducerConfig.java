package com.wolfhack.user.command.config;

import com.wolfhack.user.command.model.event.UserEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserProducerConfig {

	private final String bootstrapServers;

	public UserProducerConfig(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	@Bean
	public ProducerFactory<String, UserEvent> producerFactory() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		properties.put(JsonSerializer.TYPE_MAPPINGS, "hat:com.wolfhack.user.command.model.event.UserCreate");
		return new DefaultKafkaProducerFactory<>(properties);
	}

	@Bean
	public KafkaTemplate<String, UserEvent> kafkaTemplate(ProducerFactory<String, UserEvent> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}


}
