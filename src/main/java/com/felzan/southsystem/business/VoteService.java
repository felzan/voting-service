package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.VoteDTO;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.entity.Vote;
import com.felzan.southsystem.entity.VotePK;
import com.felzan.southsystem.exception.SessionDoesNotExistException;
import com.felzan.southsystem.exception.SessionIsClosedException;
import com.felzan.southsystem.exception.VoteDuplicatedException;
import com.felzan.southsystem.repository.TopicRepository;
import com.felzan.southsystem.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

	VoteRepository repository;
	TopicRepository topicRepository;

	public void save(VoteDTO dto) {
		Optional<Topic> optionalTopic = topicRepository.findById(dto.getTopicId());
		if (!optionalTopic.isPresent()) {
			throw new SessionDoesNotExistException();
		}
		Topic topic = optionalTopic.get();

		boolean isSessionEnded = topic.getSessionEnd().isBefore(LocalDateTime.now());
		if (isSessionEnded) {
			throw new SessionIsClosedException();
		}

		boolean voteExists = repository.existsById(new VotePK(dto.getTopicId(), dto.getUserId()));
		if (voteExists) {
			throw new VoteDuplicatedException();
		}

		Vote vote = new Vote(dto.getVote(), dto.getTopicId(), dto.getUserId());
		repository.save(vote);
	}
}
