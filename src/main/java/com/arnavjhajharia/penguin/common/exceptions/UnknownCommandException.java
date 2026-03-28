package com.arnavjhajharia.penguin.common.exceptions;

/**
 * Thrown to indicate that the user entered a command that does not exist
 * or is not recognized by the Penguin application.
 * <p>
 * This exception provides feedback to the user by including the unrecognized
 * command string in its message.
 */
public final class UnknownCommandException extends PenguinException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@code UnknownCommandException} for the given command.
     *
     * @param cmd the unrecognized command entered by the user
     */
    public UnknownCommandException(String cmd) {
        super("It's chill but what do you mean lol: \"" + String.valueOf(cmd) + "\".");
    }
}
