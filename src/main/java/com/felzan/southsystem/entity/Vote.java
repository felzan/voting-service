package com.felzan.southsystem.entity;

import com.felzan.southsystem.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(VotePK.class)
public class Vote {

	@Enumerated(EnumType.STRING)
	private VoteEnum vote;
	@Id
	private Integer topicId;
	@Id
	private Integer userId;
}
