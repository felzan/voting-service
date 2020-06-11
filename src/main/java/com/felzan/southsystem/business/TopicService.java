package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.exception.NotFoundException;
import com.felzan.southsystem.exception.SessionAlreadyOpenException;
import com.felzan.southsystem.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicService {

	TopicRepository repository;

	public Topic save(TopicDTORequest request) {
		Topic topic = new Topic();
		topic.setDescription(request.getDescription());
		return repository.save(topic);
	}

	public Topic startSession(Integer id, LocalDateTime sessionEnd) {
		Optional<Topic> optionalTopic = repository.findById(id);
		if (!optionalTopic.isPresent()) {
			throw new NotFoundException("This session does not exists.");
		}

		Topic topic = optionalTopic.get();

		if (topic.getSessionEnd() != null) {
			throw new SessionAlreadyOpenException();
		}
		topic.setSessionEnd(sessionEnd);
		return repository.save(topic);
	}
}
