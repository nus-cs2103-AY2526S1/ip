package lyra.command;

import lyra.task.TaskList;
import lyra.task.Deadline;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;
import lyra.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Command implementation for adding a new deadline task to the task list.
 * Creates a task with a description and a specific deadline date.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String byRaw;
    
    /**
     * Constructs a new AddDeadlineCommand with the specified description and deadline.
     *
     * @param description the description of the deadline task to be created
     * @param by the deadline date in string format (will be parsed to LocalDate)
     */
    public AddDeadlineCommand(String description, String by) {
        assert description != null && !description.trim().isEmpty() : "description must not be null or empty";
        assert by != null && !by.trim().isEmpty() : "by must not be null or empty";
        this.description = description;
        this.byRaw = by;
    }
    
    /**
     * Executes the add deadline command by parsing the deadline date,
     * creating a new Deadline task, adding it to the task list,
     * saving to storage, and displaying confirmation.
     *
     * @param tasks the task list to add the new deadline to
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
            LocalDate by = DateTimeUtil.parseDate(byRaw);
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            storage.save(tasks.getTasks());
            ui.showTaskAdded(task, tasks.getSize());
        } catch (DateTimeParseException e) {
            throw new LyraException("Invalid date. Please use yyyy-MM-dd, e.g., 2019-10-15.");
        }
    }
}
