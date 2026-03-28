package com.arnavjhajharia.penguin.common.exceptions;

/**
 * Thrown to indicate that a command was executed with missing or invalid arguments.
 * <p>
 * This exception provides feedback on the expected format of the command input,
 * making it easier for users to correct their input.
 */
public final class MissingArgumentException extends PenguinException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code MissingArgumentException} with a message that
     * specifies the expected format of the command input.
     *
     * @param expected a description of the correct or expected command format
     */
    public MissingArgumentException(String expected) {
        super("Missing/invalid arguments. Expected: " + String.valueOf(expected));
    }
}
