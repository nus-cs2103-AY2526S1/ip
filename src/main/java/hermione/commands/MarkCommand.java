package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.TaskList;

/**
 * Represents a command to mark a task as done in the Hermione application.
 */
public class MarkCommand extends Command {
    public MarkCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to mark a task as done by its ID.
     * Validates the input, checks if the task ID is valid,
     * and updates the task's completion status in the storage.
     *
     * @return A confirmation message indicating the task has been marked as done.
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
            storage.setTaskCompletion(task, true);

            return "Fantastic! I've marked this task as completed:\n" + task.toString()
                    + "\nWell done! You're making excellent progress!";
        } catch (NumberFormatException e) {
            throw new TaskValidationException("Invalid task id");
        } catch (IndexOutOfBoundsException e) {
            throw new TaskValidationException("Task id does not exist");
        }
    }
}
