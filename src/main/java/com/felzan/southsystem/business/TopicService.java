package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.ReportDTO;
import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.exception.NotFoundException;
import com.felzan.southsystem.exception.SessionAlreadyOpenException;
import com.felzan.southsystem.repository.ReportDAO;
import com.felzan.southsystem.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicService {

	TopicRepository repository;
	ReportDAO reportDAO;

	public ReportDTO report(Integer id) {
		Optional<Topic> optionalTopic = repository.findById(id);

		if (!optionalTopic.isPresent()) {
			throw new NotFoundException();
		}
		Topic topic = optionalTopic.get();
		if (LocalDateTime.now().isBefore(topic.getSessionEnd())) {
			throw new ResponseStatusException(HttpStatus.TOO_EARLY);
		}
		return reportDAO.report(id);
	}

	public Topic save(TopicDTORequest request) {
		Topic topic = new Topic();
		topic.setDescription(request.getDescription());
		return repository.save(topic);
	}

	public Topic startSession(Integer id, LocalDateTime sessionEnd) {
		Optional<Topic> optionalTopic = repository.findById(id);
		if (!optionalTopic.isPresent()) {
			throw new NotFoundException();
		}

		Topic topic = optionalTopic.get();

		if (topic.getSessionEnd() != null) {
			throw new SessionAlreadyOpenException();
		}
		topic.setSessionEnd(sessionEnd);
		return repository.save(topic);
	}
}
