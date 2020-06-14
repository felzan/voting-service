package com.felzan.southsystem.business;

import com.felzan.southsystem.dto.TopicDTORequest;
import com.felzan.southsystem.entity.Topic;
import com.felzan.southsystem.exception.NotFoundException;
import com.felzan.southsystem.exception.SessionAlreadyOpenException;
import com.felzan.southsystem.repository.TopicRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest {

    @InjectMocks
    TopicService service;

    @Mock
    TopicRepository repository;

    @Test
    public void shouldSave() {
        TopicDTORequest request = new TopicDTORequest();
        request.setDescription("description");

        Topic topic = new Topic();
        topic.setDescription(request.getDescription());

        when(repository.save(topic)).thenReturn(topic);

        service.save(request);
    }

    @Test
    public void shouldStartSession() {
        Integer id = 1;
        Topic topic = new Topic();
        topic.setDescription("description");
        Optional<Topic> optionalTopic = Optional.of(topic);

        when(repository.findById(id)).thenReturn(optionalTopic);
        when(repository.save(any(Topic.class))).thenReturn(topic);

        service.startSession(id, LocalDateTime.now());
    }

    @Test(expected = NotFoundException.class)
    public void shouldStartSessionThenThrowNotFoundException() {
        Integer id = 1;
        Optional<Topic> optionalTopic = Optional.empty();

        when(repository.findById(id)).thenReturn(optionalTopic);

        service.startSession(id, LocalDateTime.now());
    }

    @Test(expected = SessionAlreadyOpenException.class)
    public void shouldStartSessionThenThrowSessionAlreadyOpenException() {
        Integer id = 1;
        Topic topic = new Topic();
        topic.setDescription("description");
        topic.setSessionEnd(LocalDateTime.now());
        Optional<Topic> optionalTopic = Optional.of(topic);

        when(repository.findById(id)).thenReturn(optionalTopic);

        service.startSession(id, LocalDateTime.now());
    }
}
