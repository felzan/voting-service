package com.felzan.southsystem.api.v1;

import com.felzan.southsystem.business.TopicService;
import com.felzan.southsystem.dto.TopicDTO;
import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

	TopicService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> createTopic(@RequestBody TopicDTORequest request) {
		if (Objects.isNull(request.getSessionEnd())) {
			request.setSessionEnd(LocalDateTime.now());
		}
		return ResponseEntity.ok(service.save(request));
	}
}
