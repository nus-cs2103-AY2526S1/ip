package steven.command;

import steven.exception.StevenException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Adds a deadline task to the task list.
 * The command encapsulates the input string representing the task details
 * and handles execution including error handling.
 */
public class AddDeadlineTaskCommand implements Command {
    private final String input;

    /**
     * Constructs an AddDeadlineTaskCommand with the specified input.
     *
     * @param input The input string describing the deadline task.
     */
    public AddDeadlineTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.addDeadlineTask(input, storage);
        } catch (StevenException e) {
            return e.getMessage();
        }
    }
}
