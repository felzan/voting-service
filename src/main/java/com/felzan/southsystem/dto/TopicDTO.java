package com.felzan.southsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TopicDTO {

	private Integer id;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime sessionEnd;

}
