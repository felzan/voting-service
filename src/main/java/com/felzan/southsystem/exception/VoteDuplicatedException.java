package com.felzan.southsystem.exception;

public class VoteDuplicatedException extends RuntimeException {

    private static final String MESSAGE = "This vote has already been counted.";

    public VoteDuplicatedException() {
        super(MESSAGE);
    }
}
