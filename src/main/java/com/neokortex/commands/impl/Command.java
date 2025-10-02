package com.neokortex.commands.impl;

/**
 * An interface representing an executable command. It simply gets executed, and when it does, it returns
 * a {@link CommandResponse}
 *
 * <p>
 * The {@code Command} interface enforces all Commands to have at least an execute method.
 * </p>
 *
 * @see CommandResponse
 */
public interface Command {
    /**
     * Executes the command. The resulting execution is a CommandResponse that contains
     * the directive for the DialoguePath to execute as well as any additional results that
     * needs to be attached to the CommandResponse.
     *
     * @return a {@link CommandResponse} that encapsulates the result of executing a Command.
     */
    CommandResponse execute();
}
