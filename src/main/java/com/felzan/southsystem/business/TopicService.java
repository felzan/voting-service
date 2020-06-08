package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.TopicDTO;
import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TopicService {

	TopicRepository repository;

	public Topic save(TopicDTORequest request) {
		Topic topic = new Topic();
		topic.setDescription(request.getDescription());
		topic.setSessionEnd(request.getSessionEnd());
		return repository.save(topic);
	}
}
