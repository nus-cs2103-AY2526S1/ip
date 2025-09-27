package steven.command;

import steven.storage.Storage;
import steven.task.TaskList;

/**
 * Deletes a task from the task list.
 * The command encapsulates the input string representing the task index
 * and handles execution and interaction with storage.
 */
public class DeleteTaskCommand implements Command {
    private final String input;

    /**
     * Constructs a new DeleteTaskCommand with the given input.
     *
     * @param input String containing the index of the task to be deleted.
     */
    public DeleteTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Storage storage, TaskList tasks) {
        return tasks.deleteTask(input, storage);
    }
}
