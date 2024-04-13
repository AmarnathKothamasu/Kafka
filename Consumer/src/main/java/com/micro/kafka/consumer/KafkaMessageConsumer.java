package com.micro.kafka.consumer;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.dto.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageConsumer {

	@Value("${app.topic.name}")
	private String topicName;
	
	@RetryableTopic(attempts = "4") // 3 topic N-1
	@KafkaListener(topics = "topicName", groupId = "group-1")
	public void consumeEvents(User user, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
			@Header(KafkaHeaders.OFFSET) long offset) {
		try {
			System.out.println("Received: {} from {} offset {}  " + new ObjectMapper().writeValueAsString(user) + "  "
					+ topic + "  " + offset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
