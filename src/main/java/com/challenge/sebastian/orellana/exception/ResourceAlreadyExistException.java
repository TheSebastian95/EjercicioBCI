package com.challenge.sebastian.orellana.exception;

public class ResourceAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceAlreadyExistException(String message) {
        super(message);
    }

    public ResourceAlreadyExistException(String message, Throwable e) {
        super(message, e);
    }
}

