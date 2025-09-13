package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.TaskList;

/**
 * Represents a command to delete a task in the Hermione application.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to delete a task by its ID.
     * Validates the input, checks if the task ID is valid,
     * and removes the task from the storage.
     *
     * @return A confirmation message indicating the task has been removed and the
     *         updated task count.
     * @throws TaskValidationException If the task ID is invalid or does not exist.
     */
    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskValidationException("Task id cannot be empty");
        }
        try {
            int taskId = Integer.parseInt(argument);
            Task removedTask = storage.deleteTask(taskId - 1);

            TaskList tasks = storage.getTasks();
            return "Right then! I've removed this task from your list:\n"
                    + removedTask.toString()
                    + "\nNow you have %d tasks remaining. One less thing to worry about!".formatted(tasks.getSize());
        } catch (NumberFormatException e) {
            throw new TaskValidationException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskValidationException("Task id does not exist");
        }
    }
}
