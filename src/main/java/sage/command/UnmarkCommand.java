package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.TaskList;
import sage.task.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     * @param taskIndex The zero-based index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command.
     * Marks the task at the specified index as not done in the TaskList,
     * saves the updated task list to storage, and displays a confirmation message.
     * @param tasks The TaskList to operate on.
     * @param ui The Ui to display messages to the user.
     * @param storage The Storage to save the updated task list.
     * @throws SageException If the task index is invalid.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Ui ui, Storage storage) throws SageException {
        tasks.unmarkTask(taskIndex);
        storage.save(tasks.getTasks());
        return ui.showMessageAndReturn("OK, I've marked this task as not done yet:\n   " + tasks.getTask(taskIndex));
    }
}
