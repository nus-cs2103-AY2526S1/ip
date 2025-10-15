package bro.commands;

import bro.tasks.Tasks;

/**
 * Represents a command that returns an error message when executed.
 */
public class CommandError extends Command {
    private final String errorMessage;

    /**
     * Creates a new CommandError with the specified error message.
     *
     * @param errorMessage The error message to be returned when the command is
     *                     executed.
     */
    public CommandError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the command and returns the error message.
     *
     * @return The error message.
     */
    @Override
    public String execute(Tasks tasks) {
        return errorMessage;
    }

}
