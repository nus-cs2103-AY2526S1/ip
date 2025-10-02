package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;

/**
 * Greets the user
 *
 * <p>
 * The {@code GreetCommand} greets the user.
 * </p>
 *
 * @see Command
 */
public class GreetCommand implements Command {
    /**
     * Constructs a new {@code GreetCommand}
     */
    public GreetCommand() {
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(DialoguePath.GREET, ResponseStatus.SUCCESS);
    }
}
