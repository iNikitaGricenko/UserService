package com.wolfhack.user.query.config;

import com.wolfhack.user.query.model.event.UserEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class UserConsumeConfig {

	private final String bootstrapServers;

	public UserConsumeConfig(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	@Bean
	public ConsumerFactory<String, UserEvent> multicastMessageConsumerFactory() {
		Map<String, Object> properties = getDefaultConsumerFactoryProperties();
		return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), new JsonDeserializer<>(UserEvent.class));
	}

	private Map<String, Object> getDefaultConsumerFactoryProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		properties.put(JsonDeserializer.TYPE_MAPPINGS, "hat:com.wolfhack.user.query.model.event.UserEvent");
		properties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.wolfhack.*");
		return properties;
	}


}
