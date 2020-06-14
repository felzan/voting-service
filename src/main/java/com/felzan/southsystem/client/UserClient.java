package com.felzan.southsystem.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserClient {

	@Value("${usersUrl}")
	private String url;

	public UserDTO getUser(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity(url + id, UserDTO.class).getBody();
	}
}
