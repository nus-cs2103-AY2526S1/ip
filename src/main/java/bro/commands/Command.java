package bro.commands;

import bro.tasks.Tasks;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    public abstract String execute(Tasks tasks);
}
