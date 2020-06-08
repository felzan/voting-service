package com.felzan.southsystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Data
@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime sessionEnd;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
}
