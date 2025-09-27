package steven.command;

import steven.exception.StevenException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Adds an event task to the task list.
 * The command encapsulates the input string representing the event details
 * and handles execution including error handling.
 */
public class AddEventTaskCommand implements Command {
    private final String input;

    /**
     * Constructs an AddEventTaskCommand with the specified input.
     *
     * @param input The input string describing the event task.
     */
    public AddEventTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.addEventTask(input, storage);
        } catch (StevenException e) {
            return e.getMessage();
        }
    }
}
