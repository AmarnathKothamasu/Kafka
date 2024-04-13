package com.micro.kafka.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.dto.User;

@Service
public class KafkaMessagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${app.topic.name}")
	private String topicName;

	public void sendEvents(User user) {
		try {
			kafkaTemplate.send(topicName, user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
