package com.felzan.southsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SessionAlreadyOpenException extends RuntimeException {

    private static final String MESSAGE = "This session is already open.";

    public SessionAlreadyOpenException() {
        super(MESSAGE);
    }
}
