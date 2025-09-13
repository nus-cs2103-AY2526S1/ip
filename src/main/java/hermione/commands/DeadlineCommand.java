package hermione.commands;

import java.time.LocalDateTime;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Deadline;
import hermione.tasks.Task;
import hermione.utils.DateUtils;
import hermione.utils.UiUtils;

/**
 * Represents a command to create a new Deadline task in the Hermione
 * application.
 */
public class DeadlineCommand extends Command {

    private static final String BY_FLAG = "/by";

    public DeadlineCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to create a new Deadline task.
     * Validates the input format, extracts the description and deadline,
     * and adds the new task to the storage.
     *
     * @return A confirmation message indicating the task has been added and the
     *         updated task count.
     * @throws TaskValidationException If the input format is incorrect or required
     *                                 fields are missing.
     */
    @Override
    public String execute() {
        if (!argument.contains(BY_FLAG)) {
            throw new TaskValidationException("Deadline Task must contain '/by' flag");
        }

        String[] split = argument.split(BY_FLAG);

        if (split.length != 2) {
            throw new TaskValidationException("Deadline Task format must be: deadline {description} /by {deadline}");
        }

        String description = argument.split(BY_FLAG)[0].trim();
        String by = argument.split(BY_FLAG)[1].trim();

        if (description.isBlank()) {
            throw new TaskValidationException("Deadline Task description cannot be empty.");
        }

        if (by.isBlank()) {
            throw new TaskValidationException("Deadline Task deadline cannot be empty.");
        }

        LocalDateTime parsedBy = DateUtils.parseDateString(by);

        Task newTask = new Deadline(description, false, parsedBy);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
