package com.felzan.southsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class SessionClosedException extends RuntimeException {

    private static final String MESSAGE = "This session is closed. Cannot receive more votes";

    public SessionClosedException() {
        super(MESSAGE);
    }
}
