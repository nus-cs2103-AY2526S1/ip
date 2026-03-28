package com.arnavjhajharia.penguin.common.exceptions;

/**
 * Base exception type for all custom exceptions in the Penguin application.
 * <p>
 * Extends {@link Exception} to allow specific, meaningful error handling
 * within the application while preserving standard Java exception behavior.
 * All application-specific exceptions should subclass {@code PenguinException}.
 */
public abstract class PenguinException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code PenguinException} with the specified detail message.
     *
     * @param message the detail message describing the cause of the exception
     */
    public PenguinException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code PenguinException} with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of this exception
     */
    public PenguinException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code PenguinException} with the specified cause.
     *
     * @param cause the cause of this exception
     */
    public PenguinException(Throwable cause) {
        super(cause);
    }
}
