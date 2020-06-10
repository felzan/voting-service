package com.felzan.southsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotePK implements Serializable {

	private Integer topicId;
	private Integer userId;
}
