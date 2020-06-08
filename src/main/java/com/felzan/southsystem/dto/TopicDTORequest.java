package com.felzan.southsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicDTORequest {

	private String description;
	private LocalDateTime sessionEnd;

}
