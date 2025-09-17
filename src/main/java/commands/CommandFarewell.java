package commands;

import app.Messages;
import app.TaskList;
import errors.BoopError;

/**
 * This command signals that the program should exit
 * after displaying a farewell message.
 */
public class CommandFarewell extends Command {
    @Override
    public void execute(TaskList tasklist) throws BoopError {
    }

    @Override
    public String getMessage() {
        return Messages.COMMAND_FAREWELL;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
