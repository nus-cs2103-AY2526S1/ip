package steven.command;

import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Represents an unrecognized command.
 * This command is executed when the user enters input that does not match
 * any valid command, and simply returns a placeholder response.
 */
public class UnknownCommand implements Command {

    /**
     * Executes this command by returning a placeholder string to indicate
     * that the user input was not recognized as a valid command.
     *
     * @param storage Storage object responsible for persisting the task data
     *                (not modified by this command).
     * @param tasks TaskList containing the tasks (not modified by this command).
     * @return Placeholder message indicating the command is unknown.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        return "\t????";
    }
}
