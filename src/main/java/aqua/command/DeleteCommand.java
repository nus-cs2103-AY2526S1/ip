package aqua.command;

import aqua.exception.InvalidArgumentException;
import aqua.exception.StorageException;
import aqua.task.Task;
import aqua.task.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String commandArgs;

    /**
     * Creates a Delete command that deletes a task from Task list.
     *
     * @param commandArgs Rest of the user's input after the "delete" command
     */
    public DeleteCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskList List of Task
     * @throws InvalidArgumentException if ID of task to be deleted is invalid
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException, StorageException {
        try {
            int index = Integer.parseInt(commandArgs) - 1; // -1 since ArrayList is 0-indexed
            if (index < 0 || index >= taskList.size()) {
                throw new InvalidArgumentException("Invalid ID");
            }

            Task task = taskList.delete(index);

            return String
                    .format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", task,
                            taskList.size());
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("Invalid ID");
        }
    }
}
