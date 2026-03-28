package com.arnavjhajharia.penguin.logic.commands;

/**
 * Represents the result of executing a {@link Command}.
 * <p>
 * A {@code CommandResult} contains:
 * <ul>
 *   <li>A message to be shown to the user (feedback or confirmation).</li>
 *   <li>A flag indicating whether the command signals program termination.</li>
 * </ul>
 * <p>
 * Use the static factory methods {@link #of(String)} for normal results and
 * {@link #exit(String)} for exit results.
 */
public class CommandResult {

    /** The feedback or output message associated with the command execution. */
    private final String message;

    /** Whether this result signals that the program should exit. */
    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult}.
     *
     * @param message the feedback message for the user
     * @param isExit  whether this result signals program termination
     */
    public CommandResult(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Returns the message associated with this command result.
     *
     * @return the feedback message
     */
    public String message() {
        return message;
    }

    /**
     * Returns whether this result indicates program termination.
     *
     * @return {@code true} if the command signals exit; {@code false} otherwise
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Creates a non-exit {@code CommandResult} with the given message.
     *
     * @param message the feedback message
     * @return a {@code CommandResult} indicating normal execution
     */
    public static CommandResult of(String message) {
        return new CommandResult(message, false);
    }

    /**
     * Creates an exit {@code CommandResult} with the given message.
     *
     * @param message the feedback message
     * @return a {@code CommandResult} indicating program termination
     */
    public static CommandResult exit(String message) {
        return new CommandResult(message, true);
    }
}
