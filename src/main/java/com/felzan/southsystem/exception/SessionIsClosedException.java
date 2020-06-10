package com.felzan.southsystem.exception;

public class SessionIsClosedException extends RuntimeException {

    private static final String MESSAGE = "This session is closed.";

    public SessionIsClosedException() {
        super(MESSAGE);
    }
}
