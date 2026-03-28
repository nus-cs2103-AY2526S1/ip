package com.arnavjhajharia.penguin.logic.commands;

import com.arnavjhajharia.penguin.model.TaskList;

/**
 * Represents a command that signals the program to exit.
 * <p>
 * The actual termination behavior (e.g., shutting down the application)
 * is handled externally by the Simulator, allowing tests to intercept
 * and verify exit behavior without terminating the test process.
 */
public final class ByeCommand implements Command {

    /**
     * Executes the {@code ByeCommand}.
     * <p>
     * This does not directly terminate the program. Instead, it returns a
     * {@link CommandResult} marked as an exit signal, containing the message "bye".
     *
     * @param tasks the current {@link TaskList}; unused in this command
     * @return a {@link CommandResult} signaling program exit
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        // Actual exit message handled by Simulator (so tests can control exit)
        return CommandResult.exit("bye");
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return {@code true}, since this command is intended to end the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
