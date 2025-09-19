package jerome.command;

import jerome.TaskList;
import jerome.Ui;
import jerome.Storage;
import jerome.JeromeException;
import jerome.task.Event;
import jerome.task.Task;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add an Event task.
 */
public class EventCommand extends Command {
    private String description;
    private String start;
    private String end;

    public EventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Adds Event task to task list and saves updated task list to storage.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save or load task data.
     * @return response message to indicate successful addition to be shown in the gui.
     * @throws JeromeException If the command cannot be executed properly.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JeromeException {
        try {
            Task task = new Event(description, start, end);
            tasks.add(task);
            storage.save(tasks.getAll());
            return ui.showAddition(task, tasks);
        } catch (DateTimeParseException e) {
            throw new JeromeException("Please provide a valid date in this format yyyy-mm-dd");
        }
    }
}
