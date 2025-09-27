package steven.command;

import steven.exception.StevenException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Represents a command to add a fixed-duration task to the task list.
 * The command encapsulates the input string representing the event details
 * and handles execution including error handling.
 */
public class AddFixedDurationTaskCommand implements Command {
    private final String input;

    /**
     * Constructs an AddFixedDurationTaskCommand with the given input.
     *
     * @param input the raw user input specifying the fixed-duration task
     */
    public AddFixedDurationTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.addFixedDurationTask(input, storage);
        } catch (StevenException e) {
            return e.getMessage();
        }
    }
}
