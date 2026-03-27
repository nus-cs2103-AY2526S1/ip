package david.command;

import david.exception.DavidException;
import david.exception.EventException;
import david.storage.Storage;
import david.task.Event;
import david.task.Task;
import david.task.TaskList;

/**
 * Represents a command to create an Event task.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Initialises a command for an Event Task.
     *
     * @param args User input.
     * @throws DavidException If input is not in valid format.
     */
    public EventCommand(String args) throws DavidException {
        String[] parts = args.split("/from", 2);
        if (parts.length < 2) {
            throw new EventException("You know event must have /from and /to right");
        }

        String[] times = parts[1].split("/to", 2);
        if (times.length < 2) {
            throw new EventException("You know the format for event is like that right: event <task> /from <start> /to <end>");
        }

        this.description = parts[0].trim();
        this.from = times[0].trim();
        this.to = times[1].trim();

        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        assert from != null && !from.isEmpty() : "From datetime should not be null or empty";
        assert to != null && !to.isEmpty() : "To datetime should not be null or empty";
    }

    /**
     * Creates an Event task and adds it into the user's TaskList.
     *
     * @param tasks List of Tasks.
     * @param storage User's data storage.
     * @return Text to be displayed.
     * @throws DavidException If the user's input does not follow the deadline format: event <task> /from <start> /to <end>.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DavidException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.save(tasks);
        return     "I add this task liao:\n    " + task;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws DavidException {
        tasks.deleteTask(tasks.size());
        storage.save(tasks);
    }
}
