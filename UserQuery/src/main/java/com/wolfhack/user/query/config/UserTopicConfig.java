package com.wolfhack.user.query.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserTopicConfig {

	private final String bootstrapServers;
	private final String userEventTopic;

	public UserTopicConfig(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
	                       @Value("${event.user.topic}") String userEventTopic) {
		this.bootstrapServers = bootstrapServers;
		this.userEventTopic = userEventTopic;
	}

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic multicastMessageTopic() {
		return TopicBuilder.name(userEventTopic).build();
	}
}
