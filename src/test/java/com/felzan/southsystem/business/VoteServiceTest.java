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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    private final static Integer TOPIC_ID = 1;
    private final static Integer USER_ID = 42;

    @InjectMocks
    VoteService service;

    @Mock
    VoteRepository repository;

    @Mock
    TopicRepository topicRepository;

    @Test
    public void shouldSave() {
        VotePK votePK = buildVotePK();
        Topic topic = buildTopic(true);
        VoteDTO voteDTO = buildVoteDTO();

        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));
        when(repository.existsById(votePK)).thenReturn(false);

        when(repository.save(any(Vote.class))).thenReturn(new Vote());

        service.save(voteDTO);
    }

    @Test(expected = SessionDoesNotExistException.class)
    public void shouldSaveThenThrowSessionDoesNotExistException() {
        VoteDTO voteDTO = buildVoteDTO();

        when(topicRepository.findById(anyInt())).thenReturn(Optional.empty());

        service.save(voteDTO);
    }

    @Test(expected = SessionIsClosedException.class)
    public void shouldSaveThenThrowSessionIsClosedException() {
        Topic topic = buildTopic(false);
        VoteDTO voteDTO = buildVoteDTO();

        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));

        service.save(voteDTO);
    }

    @Test(expected = VoteDuplicatedException.class)
    public void shouldSaveThenThrowVoteDuplicatedException() {
        VotePK votePK = new VotePK(TOPIC_ID, USER_ID);
        Topic topic = buildTopic(true);
        VoteDTO voteDTO = buildVoteDTO();

        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));
        when(repository.existsById(votePK)).thenReturn(true);

        service.save(voteDTO);
    }

    private static Topic buildTopic(boolean sessionOpen) {
        Topic topic = new Topic();
        topic.setId(TOPIC_ID);
        topic.setDescription("description");
        if (sessionOpen) {
            topic.setSessionEnd(LocalDateTime.now().plusMinutes(2));
        } else {
            topic.setSessionEnd(LocalDateTime.now().minusMinutes(2));
        }
        return topic;
    }

    private static VotePK buildVotePK() {
        return new VotePK(TOPIC_ID, USER_ID);
    }

    private static VoteDTO buildVoteDTO() {
        return new VoteDTO(VoteEnum.NO, TOPIC_ID, USER_ID);
    }

}