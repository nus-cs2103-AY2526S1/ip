package com.neokortex.exceptions;

/**
 * Represents an exception that should be impossible, where a Command was not found where it was supposed
 * to be.
 */
public class MissingCommandException extends RuntimeException {
    public MissingCommandException(String message) {
        super(message);
    }
}
