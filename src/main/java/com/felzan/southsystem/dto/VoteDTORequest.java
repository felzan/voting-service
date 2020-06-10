package com.felzan.southsystem.dto;

import com.felzan.southsystem.enums.VoteEnum;
import lombok.Data;

@Data
public class VoteDTORequest {

    private VoteEnum vote;
}
