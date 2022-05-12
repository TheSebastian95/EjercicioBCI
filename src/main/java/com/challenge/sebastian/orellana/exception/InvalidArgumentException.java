package com.challenge.sebastian.orellana.exception;

public class InvalidArgumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable e) {
        super(message, e);
    }
}

