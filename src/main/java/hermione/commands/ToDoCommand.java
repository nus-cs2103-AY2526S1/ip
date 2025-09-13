package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.ToDo;
import hermione.utils.UiUtils;

/**
 * Represents a command to create a new ToDo task in the Hermione application.
 */
public class ToDoCommand extends Command {
    public ToDoCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to create a new ToDo task.
     * Validates the input format, checks if the description is provided,
     * and adds the new task to the storage.
     *
     * @return A confirmation message indicating the task has been added and the
     *         updated task count.
     * @throws TaskValidationException If the input format is incorrect or required
     *                                 fields are missing.
     */
    @Override
    public String execute() {
        if (argument.isBlank()) {
            throw new TaskValidationException("Todo Task format must be: todo {description}");
        }

        Task newTask = new ToDo(argument, false);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
