package com.felzan.southsystem.exception;

public class SessionDoesNotExistException extends RuntimeException {

    private static final String MESSAGE = "This session does not exists.";

    public SessionDoesNotExistException() {
        super(MESSAGE);
    }
}
