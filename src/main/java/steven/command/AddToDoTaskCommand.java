package steven.command;

import steven.exception.StevenException;
import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Adds a to-do task to the task list.
 * The command encapsulates the input string representing the task details
 * and handles execution including error handling.
 */
public class AddToDoTaskCommand implements Command {
    private final String input;

    /**
     * Constructs a new AddToDoTaskCommand with the given input.
     *
     * @param input String containing the details of the to-do task to be added.
     */
    public AddToDoTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        try {
            return tasks.addToDoTask(input, storage);
        } catch (StevenException e) {
            return e.getMessage();
        }
    }
}
