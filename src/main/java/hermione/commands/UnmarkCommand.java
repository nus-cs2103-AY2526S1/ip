package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.TaskList;

/**
 * Represents a command to unmark a task as not done in the Hermione
 * application.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to mark a task as not done by its ID.
     * Validates the input, checks if the task ID is valid,
     * and updates the task's completion status in the storage.
     *
     * @return A confirmation message indicating the task has been marked as not
     *         done.
     * @throws TaskValidationException If the task ID is invalid or does not exist.
     */
    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskValidationException("Task id cannot be empty");
        }
        try {
            TaskList tasks = storage.getTasks();
            int taskId = Integer.parseInt(argument);
            Task task = tasks.getTask(taskId - 1);
            storage.setTaskCompletion(task, false);

            return "No worries! I've marked this task as not done yet:\n" + task.toString()
                    + "\nDon't worry, you'll get to it when you're ready!";
        } catch (NumberFormatException e) {
            throw new TaskValidationException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskValidationException("Task id does not exist");
        }
    }
}
