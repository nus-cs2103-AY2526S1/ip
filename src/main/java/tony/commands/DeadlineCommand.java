package tony.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import tony.exceptions.TonyException;
import tony.parsers.DateTimeManager;
import tony.storage.Storage;
import tony.tasks.Deadline;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to create a {@link Deadline} task.
 * A deadline task requires both a task description and a deadline time,
 * specified using the format <code>deadline &lt;task&gt; /by &lt;date + time(optional)&gt;</code>.
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Constructs a new {@link DeadlineCommand} by parsing the input arguments.
     *
     * @param args The raw input string containing the task description and deadline.
     * @throws TonyException If the input does not contain both a task description and a deadline.
     */
    public DeadlineCommand(String args) throws TonyException {
        String[] parts = args.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new TonyException("JARVIS, show them how it's done.\n\tdeadline <task> /by <date + time(optional)>");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    /**
     * Executes the {@code DeadlineCommand}.
     * Parses the deadline string into a {@link LocalDateTime}.
     * Creates and adds a {@link Deadline} task to the given {@link TaskList}.
     * Saves the updated task list to persistent storage via {@link Storage}.
     * Displays confirmation of the added task through the {@link UI}.
     *
     * @param tasks The {@link TaskList} to which the new task will be added.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The {@link Deadline} task that has been added to the {@link TaskList} as a {@link String}.
     * @throws TonyException If the deadline cannot be parsed into a valid date-time format.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws TonyException {
        try {
            assert tasks != null : "TaskList cannot be null";
            assert ui != null : "UI cannot be null";
            LocalDateTime deadlineDateTime = DateTimeManager.parse(this.by);
            Deadline task = new Deadline(this.description, deadlineDateTime);
            tasks.addTask(task);
            storage.save(tasks);
            return ui.showAddTask(tasks, task);
        } catch (DateTimeParseException e) {
            throw new TonyException("Let me spell it out for you:\n"
                    + "Use: dd-MM-yyyy HH:mma (e.g. 12-09-2025 6:00PM).");
        }
    }
}

