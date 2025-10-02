package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;

/**
 * Fallback command for handling unrecognized or invalid user input.
 *
 * <p>
 * The InvalidCommand is executed when the user enters a command that cannot
 * be recognized or parsed by the system. It provides friendly feedback to
 * the user indicating that the input was not understood while maintaining
 * the application's conversational tone.
 * </p>
 *
 * @see Command
 */
public class InvalidCommand implements Command {
    /**
     * Constructs a new InvalidCommand
     */
    public InvalidCommand() {
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(DialoguePath.GENERIC_FAILURE, ResponseStatus.SUCCESS);
    }
}
