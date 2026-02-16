package guibot.command;

import java.io.IOException;

import guibot.Storage;
import guibot.TaskList;
import guibot.exception.GuibotException;

/**
 * Represents a set of actions to be done.
 */
public abstract class Command {
    /**
     * Executes the required actions and returns the string to be outputted. Used for GUI.
     *
     * @param tasks TaskList required to execute actions.
     * @param storage Storage required to execute actions.
     * @return String response to be outputted.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws GuibotException, IOException;
}
