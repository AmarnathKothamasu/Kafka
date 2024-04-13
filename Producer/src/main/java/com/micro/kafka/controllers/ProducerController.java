package com.micro.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.dto.User;
import com.micro.kafka.publisher.KafkaMessagePublisher;

@RestController
@RequestMapping("/api")
public class ProducerController {

	@Autowired
	private KafkaMessagePublisher publisher;

	@GetMapping("/message")
	public String getMessage(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "Hello, " + name + "!";
	}

	@PostMapping("/message")
	public String sendMessage(@RequestBody String message) {
		return "Message sent: " + message;
	}

	@PostMapping("/publishNew")
	public ResponseEntity<?> publishEvent(@RequestBody User user) {
		try {
			publisher.sendEvents(user);
			return ResponseEntity.ok("Message published successfully");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
