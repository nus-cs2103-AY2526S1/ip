package steven.command;

import steven.exception.InvalidMarkFormatException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Unmarks a task in the task list, setting it back to not completed.
 * The command encapsulates the input string representing the task index
 * and handles execution including error handling.
 */
public class UnmarkTaskCommand implements Command {
    private final String input;

    /**
     * Constructs a new UnmarkTaskCommand with the given input.
     *
     * @param input String containing the index of the task to be unmarked.
     */
    public UnmarkTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.unmarkTask(input, storage);
        } catch (InvalidMarkFormatException e) {
            return e.getMessage();
        }
    }
}
