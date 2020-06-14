package com.felzan.southsystem.business;

import com.felzan.southsystem.client.UserClient;
import com.felzan.southsystem.client.UserDTO;
import com.felzan.southsystem.dto.VoteDTO;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.entity.Vote;
import com.felzan.southsystem.entity.VotePK;
import com.felzan.southsystem.enums.VoteEnum;
import com.felzan.southsystem.exception.NotFoundException;
import com.felzan.southsystem.exception.SessionClosedException;
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
    @Mock
    UserClient userClient;

    @Test
    public void shouldSave() {
        VotePK votePK = buildVotePK();
        Topic topic = buildTopic(true);
        VoteDTO voteDTO = buildVoteDTO();

        when(userClient.getUser(anyInt())).thenReturn(new UserDTO());
        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));
        when(repository.existsById(votePK)).thenReturn(false);

        when(repository.save(any(Vote.class))).thenReturn(new Vote());

        service.save(voteDTO);
    }

    @Test(expected = NotFoundException.class)
    public void shouldSaveThenThrowUserNotFoundException() {
        VoteDTO voteDTO = buildVoteDTO();

        doThrow(new NotFoundException()).when(userClient).getUser(USER_ID);

        service.save(voteDTO);
    }

    @Test(expected = NotFoundException.class)
    public void shouldSaveThenThrowSessionDoesNotExistException() {
        VoteDTO voteDTO = buildVoteDTO();

        when(userClient.getUser(anyInt())).thenReturn(new UserDTO());
        when(topicRepository.findById(anyInt())).thenReturn(Optional.empty());

        service.save(voteDTO);
    }

    @Test(expected = SessionClosedException.class)
    public void shouldSaveThenThrowSessionIsClosedException() {
        Topic topic = buildTopic(false);
        VoteDTO voteDTO = buildVoteDTO();

        when(userClient.getUser(anyInt())).thenReturn(new UserDTO());
        when(topicRepository.findById(anyInt())).thenReturn(Optional.of(topic));

        service.save(voteDTO);
    }

    @Test(expected = VoteDuplicatedException.class)
    public void shouldSaveThenThrowVoteDuplicatedException() {
        VotePK votePK = new VotePK(TOPIC_ID, USER_ID);
        Topic topic = buildTopic(true);
        VoteDTO voteDTO = buildVoteDTO();

        when(userClient.getUser(anyInt())).thenReturn(new UserDTO());
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
