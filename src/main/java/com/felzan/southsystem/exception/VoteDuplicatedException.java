package com.felzan.southsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VoteDuplicatedException extends RuntimeException {

    private static final String MESSAGE = "This vote has already been counted.";

    public VoteDuplicatedException() {
        super(MESSAGE);
    }
}
