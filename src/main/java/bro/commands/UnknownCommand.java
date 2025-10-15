package bro.commands;

import bro.tasks.Tasks;

/**
 * Represents a command for unknown or unrecognized inputs.
 */
public class UnknownCommand extends Command {
    /**
     * Creates a new UnknownCommand.
     */
    public UnknownCommand() {
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        return "I'm sorry bro, but I don't know what that means.";
    }
}
