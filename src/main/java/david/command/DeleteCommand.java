package david.command;

import david.exception.DavidException;
import david.storage.Storage;
import david.task.Task;
import david.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;
    private Task deletedTask;

    /**
     * Initialises a command to delete a Task.
     *
     * @param args User input.
     * @throws DavidException If input is not in valid format.
     */
    public DeleteCommand(String args) throws DavidException {
        try {
            this.index = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new DavidException("Eh give an index number after delete leh");
        }
    }

    /**
     * Deletes the Task at the given in the user's TaskList.
     *
     * @param tasks List of Tasks.
     * @param storage User's data storage.
     * @return Text to be displayed.
     * @throws DavidException If the user does not provide an index after delete.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DavidException {
        this.deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return "Ok I this task kenna remove liao:\n" + this.deletedTask;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public void undo(TaskList tasks, Storage storage){
        tasks.insertTask(this.index, this.deletedTask);
        storage.save(tasks);
    }
}
