package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.VoteDTO;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.entity.Vote;
import com.felzan.southsystem.entity.VotePK;
import com.felzan.southsystem.enums.VoteEnum;
import com.felzan.southsystem.exception.SessionDoesNotExistException;
import com.felzan.southsystem.exception.SessionIsClosedException;
import com.felzan.southsystem.exception.VoteDuplicatedException;
import com.felzan.southsystem.repository.TopicRepository;
import com.felzan.southsystem.repository.VoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @InjectMocks
    VoteService service;

    @Mock
    VoteRepository repository;

    @Mock
    TopicRepository topicRepository;

    @Test
    public void shouldSave() {
        Integer topicId = 1;
        Integer userId = 42;

        VotePK votePK = new VotePK(topicId, userId);

        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setDescription("description");
        topic.setSessionEnd(LocalDateTime.now().plusMinutes(2));

        VoteDTO voteDTO = new VoteDTO(VoteEnum.NO, topicId, userId);

        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));
        when(repository.existsById(votePK)).thenReturn(false);

        when(repository.save(any(Vote.class))).thenReturn(new Vote());

        service.save(voteDTO);
    }

    @Test(expected = SessionDoesNotExistException.class)
    public void shouldSaveThenThrowSessionDoesNotExistException() {
        Integer topicId = 1;
        Integer userId = 42;
        VoteDTO voteDTO = new VoteDTO(VoteEnum.NO, topicId, userId);

        when(topicRepository.findById(anyInt())).thenReturn(Optional.empty());

        service.save(voteDTO);
    }

    @Test(expected = SessionIsClosedException.class)
    public void shouldSaveThenThrowSessionIsClosedException() {
        Integer topicId = 1;
        Integer userId = 42;

        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setDescription("description");
        topic.setSessionEnd(LocalDateTime.now().minusMinutes(2));

        VoteDTO voteDTO = new VoteDTO(VoteEnum.NO, topicId, userId);

        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));

        service.save(voteDTO);
    }

    @Test(expected = VoteDuplicatedException.class)
    public void shouldSaveThenThrowVoteDuplicatedException() {
        Integer topicId = 1;
        Integer userId = 42;

        VotePK votePK = new VotePK(topicId, userId);

        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setDescription("description");
        topic.setSessionEnd(LocalDateTime.now().plusMinutes(2));

        VoteDTO voteDTO = new VoteDTO(VoteEnum.NO, topicId, userId);

        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));
        when(repository.existsById(votePK)).thenReturn(true);

        service.save(voteDTO);
    }
}