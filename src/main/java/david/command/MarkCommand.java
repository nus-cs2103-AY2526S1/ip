package david.command;

import david.exception.DavidException;
import david.storage.Storage;
import david.task.Task;
import david.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    /**
     * Initialises a command to mark a Task as done.
     *
     * @param args User input.
     * @throws DavidException If input is not in valid format.
     */
    public MarkCommand(String args) throws DavidException {
        try {
            this.index = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new DavidException("Eh give an index number after mark leh");
        }
    }

    /**
     * Marks the task at given index in the user's TaskList as done.
     *
     * @param tasks List of Tasks.
     * @param storage User's data storage.
     * @return Text to be displayed.
     * @throws DavidException If the user does not provide an index after mark.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DavidException {
        if (index > tasks.size()) {
            throw new DavidException("Task " + index + " does not exist leh\n");
        }
        Task task = tasks.getTask(index);
        assert task != null : "Task should never be null";
        tasks.markAsDone(index);
        storage.save(tasks);
        return "    Ok I mark as done liao:\n    " + task;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public void undo(TaskList tasks, Storage storage) throws DavidException {
        tasks.markAsUndone(index);
        storage.save(tasks);
    }
}
