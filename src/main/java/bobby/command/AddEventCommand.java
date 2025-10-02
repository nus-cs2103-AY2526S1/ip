package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.task.TaskList;
import bobby.ui.Ui;
import bobby.task.Event;
import bobby.task.Task;
import bobby.storage.Storage;

import java.time.LocalDateTime;

/**
 * Command to add a new event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an AddEventCommand with the specified description, start, and end times.
     *
     * @param description Description of the event task to add.
     * @param from Start date/time as a string.
     * @param to End date/time as a string.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the add event command, adding the new task and saving changes.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws BobbyException If an error occurs during task creation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Event event;
        try {
            event = new Event(description, from, to);
        } catch (BobbyException e) {
            throw new BobbyException("Invalid date/time for event. Please use d/M/yyyy HHmm, e.g., 19/09/2025 1800, and ensure the date exists.");
        }
        if (!event.getFrom().isBefore(event.getTo())) {
            throw new BobbyException("Event start date/time must be before end date/time.");
        }
        if (tasks.containsDuplicate(event)) {
            throw new BobbyException("An event with the same description, start, and end already exists.");
        }
        tasks.add(event);
        StringBuilder response = new StringBuilder();
        response.append("Added: ").append(event.toString()).append(System.lineSeparator());
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        try {
            storage.saveTasks(tasks.getAll());
        } catch (Exception e) {
            response.append(System.lineSeparator())
                    .append("Failed to save tasks fam: ").append(e.getMessage());
        }
        return response.toString();
    }
}