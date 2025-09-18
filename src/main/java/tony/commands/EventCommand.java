package tony.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import tony.exceptions.EventException;
import tony.exceptions.TonyException;
import tony.parsers.DateTimeManager;
import tony.storage.Storage;
import tony.tasks.Event;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to create a {@link Event} task.
 * An event task requires both a task description, a "from" time and a "to" time
 * specified using the format <code>event &lt;task&gt; /from &lt;time&gt; /to &lt;time&gt;</code>.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs a new {@link EventCommand} by parsing the input arguments.
     *
     * @param args The raw input string containing the task description and start and end dates.
     * @throws TonyException If the input does not contain both a "/from" and "/to".
     */
    public EventCommand(String args) throws TonyException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new EventException("JARVIS, show them how it's done.\n\tevent <event> "
                    + "/from <date + time(optional)> /to <date + time(optional)>");
        } else {
            String[] parts = args.split("/from", 2);
            String[] fromTo = parts[1].split("/to", 2);
            this.from = fromTo[0].trim();
            this.to = fromTo[1].trim();
            this.description = parts[0].trim();
        }
    }

    /**
     * Executes the {@code EventCommand}.
     * Parses the event string into a {@link LocalDateTime}.
     * Creates and adds a {@link Event} task to the given {@link TaskList}.
     * Saves the updated task list to persistent storage via {@link Storage}.
     * Displays confirmation of the added task through the {@link UI}.
     *
     * @param tasks The {@link TaskList} to which the new task will be added.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The {@link Event} task that has been added to the {@link TaskList} as a {@link String}.
     * @throws TonyException If the "from" or "to" time cannot be parsed into a valid date-time format.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws TonyException {
        try {
            assert tasks != null : "TaskList cannot be null";
            assert ui != null : "UI cannot be null";
            LocalDateTime fromDateTime = DateTimeManager.parse(this.from);
            LocalDateTime toDateTime = DateTimeManager.parse(this.to);
            for (Task task : tasks.getList()) {
                if (task instanceof Event) {
                    Event event = (Event) task;
                    LocalDateTime eventFrom = event.getFrom();
                    LocalDateTime eventTo = event.getTo();
                    if (fromDateTime.isBefore(eventFrom) && toDateTime.isAfter(eventTo)) {
                        throw new TonyException("Hold up! This event overlaps with an task event:\n  " + event);
                    }
                }
            }
            Event task = new Event(this.description, fromDateTime, toDateTime);
            tasks.addTask(task);
            storage.save(tasks);
            return ui.showAddTask(tasks, task);
        } catch (DateTimeParseException e) {
            throw new TonyException("Let me spell it out for you: dd-MM-yyyy HH:mma (e.g. 12-09-2025 6:00PM).");
        }
    }
}
