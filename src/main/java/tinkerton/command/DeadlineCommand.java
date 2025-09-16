package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.task.Deadline;
import tinkerton.storage.Save;

/**
 * Represents a command to add a Deadline task.
 */
public class DeadlineCommand extends Command {
    private static final String DATE_TIME_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}";

    /**
     * Constructs a DeadlineCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    public String parseDeadlineName(String fullCommand) {
        return fullCommand.substring(9, fullCommand.indexOf("/by")).trim();
    }

    public String parseDeadlineTime(String fullCommand) {
        return fullCommand.substring(fullCommand.indexOf("/by") + 3).trim();
    }

    /**
     * Executes the Deadline command, adding a new Deadline task to the list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command format is invalid.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        String fullCommand = super.getFull();
        String deadlineName = parseDeadlineName(fullCommand);
        String deadlineTime = parseDeadlineTime(fullCommand);

        if (deadlineName.isEmpty()) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        if (!fullCommand.contains("/by")) {
            throw new TinkertonException("Your deadline task has no deadline...");
        }

        if (deadlineTime.isEmpty()) {
            throw new TinkertonException("You seem to be missing some information...");
        }

        if (!deadlineTime.matches(DATE_TIME_REGEX)) {
            throw new TinkertonException("The format of your deadline should be yyyy-MM-dd HHmm!");
        }

        if (tasks.containsTaskName(deadlineName)) {
            throw new TinkertonException("This task already exists in your list.");
        }

        int prevSize = tasks.size();
        tasks.add(new Deadline(deadlineName, false, deadlineTime));
        assert tasks.size() == prevSize
                + 1 : "TaskList size should increase after adding a deadline";

        save.save(tasks);

        return "Got it, I've added this task:\n" + tasks.get(tasks.size() - 1).toString()
                + "<SPLIT>Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return false, as adding a Deadline does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
