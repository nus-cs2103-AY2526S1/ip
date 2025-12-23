package george.command;

import george.exceptions.GeorgeException;
import george.task.TaskManager;

/**
 * Represents an invalid or unrecognized command.
 * This command is used when user input does not match any known command format.
 */
public class InvalidCommand extends Command {
    @Override
    public String execute(TaskManager manager) throws GeorgeException {
        throw new GeorgeException("What are you saying???");
    }

    @Override
    public String getCommandWord() {
        return "invalid";
    }
}
