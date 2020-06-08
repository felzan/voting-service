package com.felzan.southsystem.api.v1;

import com.felzan.southsystem.business.TopicService;
import com.felzan.southsystem.dto.TopicDTO;
import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

	@Value("${defaultSessionTime:1}")
	private Integer defaultSessionTime;
	private final TopicService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> createTopic(@RequestBody TopicDTORequest request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> startSession(@PathVariable("id") Integer id,
                                              @RequestParam(value = "sessionEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime sessionEnd) throws Exception {
		if (Objects.isNull(sessionEnd)) {
			sessionEnd = LocalDateTime.now().plusMinutes(defaultSessionTime);
		}
		return ResponseEntity.ok(service.startSession(id, sessionEnd));
	}
}
