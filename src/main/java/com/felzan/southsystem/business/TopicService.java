package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.TopicDTO;
import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
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

	public Topic startSession(Integer id, LocalDateTime sessionEnd) throws Exception {
		Optional<Topic> optionalTopic = repository.findById(id);
		if (optionalTopic.isPresent()) {
			Topic topic = optionalTopic.get();
			topic.setSessionEnd(sessionEnd);
			return repository.save(topic);
		}
		throw new Exception("Topic not found");
	}
}
