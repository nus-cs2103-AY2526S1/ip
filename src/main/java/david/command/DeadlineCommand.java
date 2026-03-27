package david.command;

import david.exception.DeadlineException;
import david.exception.DavidException;
import david.storage.Storage;
import david.task.Deadline;
import david.task.Task;
import david.task.TaskList;

/**
 * Represents a command to create a Deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Initialises a command for a Deadline Task.
     *
     * @param args User input.
     * @throws DavidException If input is not in valid format.
     */
    public DeadlineCommand(String args) throws DavidException {
        String[] parts = args.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DeadlineException("You know the format for deadline is like that right: deadline <task> /by <end>");
        }

        this.description = parts[0].trim();
        this.by = parts[1].trim();

        assert description != null && !description.isEmpty() : "Deadline description should not be null or empty";
        assert by != null && !by.isEmpty() : "By datetime should not be null or empty";
    }

    /**
     * Creates a Deadline task and adds it into the user's TaskList.
     *
     * @param tasks List of Tasks.
     * @param storage User's data storage.
     * @return Text to be displayed.
     * @throws DavidException If the user's input does not follow the deadline format: deadline <task> /by <end>.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DavidException {
        Task task = new Deadline(description, by);
        assert task != null : "Task should never be null";
        tasks.addTask(task);
        storage.save(tasks);
        return "    I add this task liao:\n    " + task;
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
