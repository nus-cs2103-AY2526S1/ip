package hermione.commands;

import hermione.exceptions.TaskValidationException;
import hermione.storage.TaskStorage;
import hermione.tasks.FixedDurationTask;
import hermione.tasks.Task;
import hermione.utils.NumberUtils;
import hermione.utils.UiUtils;

/**
 * Represents a command to create a fixed duration task in the Hermione
 * application.
 */
public class FixedDurationTaskCommand extends Command {

    private static final String DURATION_FLAG = "/duration";

    public FixedDurationTaskCommand(TaskStorage storage, String argument) {
        super(storage, argument);
    }

    @Override
    public String execute() {
        int durationIndex = argument.indexOf(DURATION_FLAG);
        if (durationIndex == -1) {
            throw new TaskValidationException("Fixed Duration Task must contain '/duration' flag.");
        }

        String description = argument.substring(0, durationIndex).trim();
        if (description.isBlank()) {
            throw new TaskValidationException("Fixed Duration Task description cannot be empty.");
        }

        String duration = argument.substring(durationIndex + DURATION_FLAG.length()).trim();
        if (duration.isBlank()) {
            throw new TaskValidationException("Fixed Duration Task duration cannot be empty.");
        }

        int parsedDuration = NumberUtils.parsePositiveInt(duration);

        Task newTask = new FixedDurationTask(description, false, parsedDuration);
        storage.addTask(newTask);

        return UiUtils.getAddTaskString(newTask, storage);
    }
}
