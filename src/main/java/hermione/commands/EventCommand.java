package hermione.commands;

import java.time.LocalDateTime;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.Event;
import hermione.tasks.Task;
import hermione.utils.DateUtils;
import hermione.utils.UiUtils;

/**
 * Represents a command to create a new Event task in the Hermione application.
 */
public class EventCommand extends Command {

    private static final String FROM_FLAG = "/from";
    private static final String TO_FLAG = "/to";

    public EventCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    /**
     * Executes the command to create a new Event task.
     * Validates the input format, extracts the description, start time, and end
     * time,
     * and adds the new task to the storage.
     *
     * @return A confirmation message indicating the task has been added and the
     *         updated task count.
     * @throws TaskValidationException If the input format is incorrect or required
     *                                 fields are missing.
     */
    @Override
    public String execute() {
        int fromIndex = argument.indexOf(FROM_FLAG);
        int toIndex = argument.indexOf(TO_FLAG);

        if (fromIndex == -1 || toIndex == -1) {
            throw new TaskValidationException("Event Task must contain '/from' and '/to' flags.");
        }

        if (fromIndex >= toIndex) {
            throw new TaskValidationException("Event Task '/from' flag must come before '/to' flag");
        }

        String description = argument.substring(0, fromIndex).trim();
        String from = argument.substring(fromIndex + FROM_FLAG.length(), toIndex).trim();
        String to = argument.substring(toIndex + TO_FLAG.length()).trim();

        if (description.isBlank()) {
            throw new TaskValidationException("Event Task description cannot be empty.");
        }

        if (from.isBlank()) {
            throw new TaskValidationException("Event Task start time cannot be empty.");
        }

        if (to.isBlank()) {
            throw new TaskValidationException("Event Task end time cannot be empty.");
        }

        LocalDateTime parsedFrom = DateUtils.parseDateString(from);
        LocalDateTime parsedTo = DateUtils.parseDateString(to);

        if (parsedFrom.isAfter(parsedTo)) {
            throw new TaskValidationException("Event Task start time cannot be after end time.");
        }

        Task newTask = new Event(description, false, parsedFrom, parsedTo);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
