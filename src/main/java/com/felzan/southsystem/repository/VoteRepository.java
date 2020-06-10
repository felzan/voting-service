package com.felzan.southsystem.repository;

import com.felzan.southsystem.entity.Vote;
import com.felzan.southsystem.entity.VotePK;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, VotePK> {
}
