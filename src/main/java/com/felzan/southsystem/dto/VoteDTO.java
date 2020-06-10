package com.felzan.southsystem.dto;

import com.felzan.southsystem.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VoteDTO {

	private VoteEnum vote;
	private Integer topicId;
	private Integer userId;
}
