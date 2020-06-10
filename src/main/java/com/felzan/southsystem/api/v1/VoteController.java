package com.felzan.southsystem.api.v1;

import com.felzan.southsystem.business.VoteService;
import com.felzan.southsystem.dto.VoteDTO;
import com.felzan.southsystem.dto.VoteDTORequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/topics/", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

	VoteService service;

	@PostMapping(value = "{id}/votes",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> vote(@PathVariable("id") Integer id,
								  @RequestHeader("userId") Integer userId,
								  @RequestBody VoteDTORequest request) {
		VoteDTO vote = new VoteDTO(request.getVote(), id, userId);
		service.save(vote);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
