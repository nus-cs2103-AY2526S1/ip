package steven.command;

import steven.exception.InvalidMarkFormatException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Marks a task in the task list as completed.
 * The command encapsulates the input string representing the task index
 * and handles execution including error handling.
 */
public class MarkTaskCommand implements Command {
    private final String input;

    /**
     * Constructs a new MarkTaskCommand with the given input.
     *
     * @param input String containing the index of the task to be marked.
     */
    public MarkTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.markTask(input, storage);
        } catch (InvalidMarkFormatException e) {
            return e.getMessage();
        }
    }
}
