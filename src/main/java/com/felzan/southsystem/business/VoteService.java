package com.felzan.southsystem.business;

import com.felzan.southsystem.client.UserClient;
import com.felzan.southsystem.client.UserDTO;
import com.felzan.southsystem.dto.VoteDTO;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.entity.Vote;
import com.felzan.southsystem.entity.VotePK;
import com.felzan.southsystem.exception.NotFoundException;
import com.felzan.southsystem.exception.SessionClosedException;
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
	UserClient userClient;

	public void save(VoteDTO dto) {
		try {
			userClient.getUser(dto.getUserId());
		} catch (Exception e) {
			throw new NotFoundException("User not found!");
		}
		Optional<Topic> optionalTopic = topicRepository.findById(dto.getTopicId());
		if (!optionalTopic.isPresent()) {
			throw new NotFoundException();
		}
		Topic topic = optionalTopic.get();

		boolean isSessionEnded = topic.getSessionEnd().isBefore(LocalDateTime.now());
		if (isSessionEnded) {
			throw new SessionClosedException();
		}

		boolean voteExists = repository.existsById(new VotePK(dto.getTopicId(), dto.getUserId()));
		if (voteExists) {
			throw new VoteDuplicatedException();
		}

		Vote vote = new Vote(dto.getVote(), dto.getTopicId(), dto.getUserId());
		repository.save(vote);
	}
}
