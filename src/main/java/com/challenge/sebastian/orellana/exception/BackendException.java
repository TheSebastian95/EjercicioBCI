package com.challenge.sebastian.orellana.exception;

public class BackendException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BackendException(String message) {
        super(message);
    }

    public BackendException(String message, Throwable e) {
        super(message, e);
    }
}

