package bambam.command;

import java.io.IOException;

import bambam.BambamException;
import bambam.Messages;
import bambam.TaskList;
import bambam.TaskStorage;

/**
 * Represents the commands given by users.
 */
public abstract class Command {
    private final boolean isExitCommand;

    public Command(boolean isExitCommand) {
        this.isExitCommand = isExitCommand;
    }

    /**
     * Returns the current status of isExit.
     * @return The boolean on whether the command is an exit command.
     */
    public boolean isExitCommand() {
        return isExitCommand;
    }

    /**
     * Handles the execution of commands.
     * @param storage The Storage that saves and loads Task objects.
     * @param messages The UI interaction between the user and the chatbot.
     * @param taskList The current list of Task objects.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     * @throws IOException If an input or output operation fails.
     */
    public abstract void execute(TaskStorage storage, Messages messages,
                                 TaskList taskList) throws BambamException, IOException;

    /**
     * Handles the string for the output of Bambam chatbot.
     * @return The string to be displayed to the user.
     */
    public abstract String getString();
}

