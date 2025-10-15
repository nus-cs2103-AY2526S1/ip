package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;

import java.io.IOException;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) 
            throws RobertException, IOException {
        validateTaskIndex(tasks);
        Task removedTask = tasks.remove(taskIndex);
        storage.save(tasks);
        return "Noted. I've removed this task:\n  " + removedTask 
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
    }

    private void validateTaskIndex(TaskList tasks) throws RobertException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new RobertException("Task number out of range. You have " 
                    + tasks.size() + " task(s).");
        }
    }
}