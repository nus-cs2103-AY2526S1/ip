package lyra.command;

import lyra.task.TaskList;
import lyra.task.Event;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;
import lyra.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Command implementation for adding a new event task to the task list.
 * Creates a task with a description and a time period (from and to dates).
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String fromRaw;
    private final String toRaw;
    
    /**
     * Constructs a new AddEventCommand with the specified description and time period.
     *
     * @param description the description of the event task to be created
     * @param from the start date of the event in string format (will be parsed to LocalDate)
     * @param to the end date of the event in string format (will be parsed to LocalDate)
     */
    public AddEventCommand(String description, String from, String to) {
        assert description != null && !description.trim().isEmpty() : "description must not be null or empty";
        assert from != null && !from.trim().isEmpty() : "from date must not be null or empty";
        assert to != null && !to.trim().isEmpty() : "to date must not be null or empty";
        this.description = description;
        this.fromRaw = from;
        this.toRaw = to;
    }
    
    /**
     * Executes the add event command by parsing the start and end dates,
     * creating a new Event task, adding it to the task list,
     * saving to storage, and displaying confirmation.
     *
     * @param tasks the task list to add the new event to
     * @param ui the user interface for displaying confirmation messages
     * @param storage the storage component for persisting the updated task list
     * @throws LyraException if the date format is invalid or an error occurs during storage operations
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        assert tasks != null : "tasks must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";
        try {
            LocalDate from = DateTimeUtil.parseDate(fromRaw);
            LocalDate to = DateTimeUtil.parseDate(toRaw);
            Task task = new Event(description, from, to);
            tasks.addTask(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task, tasks.getSize());
        } catch (DateTimeParseException e) {
            throw new LyraException("Invalid date. Please use yyyy-MM-dd, e.g., 2019-10-15.");
        }
    }
}
