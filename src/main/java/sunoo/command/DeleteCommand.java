package sunoo.command;

import sunoo.exception.SunooException;
import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that deletes a task from the current tasklist.
 */
public class DeleteCommand extends Command {

    /** Index of the task to delete */
    private final int indexToDelete;

    /**
     * Creates a new DeleteCommand with the index of the task to delete.
     *
     * @param indexToDelete Index of the task to delete.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * {@inheritDoc}
     * <p>Deletes the task corresponding to the index given.</p>
     *
     * @throws SunooException If index is invalid.
     */
    @Override
    public String execute(TaskList tasks) {
        if (indexToDelete <= 0) {
            throw new SunooException("Sorry ENGENE, that's not a valid task index!");
        }
        if (indexToDelete > tasks.getNumTasks()) {
            throw new SunooException("Sorry ENGENE, you don't have that many tasks!");
        }
        int numTasksBefore = tasks.getNumTasks();
        String response = Ui.joinLines(
                "Ok, ENGENE! I've removed this task:",
                tasks.deleteTask(indexToDelete).toString(),
                "Now you have " + tasks.getNumTasks()
                        + " task(s) in the list left, hwaiting!");
        int numTasksAfter = tasks.getNumTasks();
        assert numTasksBefore - 1 == numTasksAfter;
        return Ui.wrapWithHorizontalLines(response);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
