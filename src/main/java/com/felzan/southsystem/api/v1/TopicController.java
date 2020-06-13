package com.felzan.southsystem.api.v1;

import com.felzan.southsystem.business.TopicService;
import com.felzan.southsystem.dto.ReportDTO;
import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/topics", produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

	@Value("${defaultSessionTime:1}")
	private Integer defaultSessionTime;
	private final TopicService service;

	@GetMapping("{id}")
	public ResponseEntity<?> report(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.report(id));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Topic> createTopic(@RequestBody TopicDTORequest request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Topic> startSession(@PathVariable("id") Integer id,
                                              @RequestParam(value = "sessionEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime sessionEnd) throws Exception {
		if (sessionEnd == null) {
			sessionEnd = LocalDateTime.now().plusMinutes(defaultSessionTime);
		}
		return ResponseEntity.ok(service.startSession(id, sessionEnd));
	}
}
