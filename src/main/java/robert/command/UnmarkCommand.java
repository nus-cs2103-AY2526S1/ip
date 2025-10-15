package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws RobertException, IOException {
        validateTaskIndex(tasks);
        tasks.unmarkTask(taskIndex);
        storage.save(tasks);
        return "OK, I've marked this task as not done yet:\n  " + tasks.get(taskIndex);
    }

    private void validateTaskIndex(TaskList tasks) throws RobertException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new RobertException("Task number out of range. You have " 
                    + tasks.size() + " task(s).");
        }
    }
}