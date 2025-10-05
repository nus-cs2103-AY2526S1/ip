package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.TaskList;
import sage.task.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     * @param taskIndex The zero-based index of the task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command.
     * Marks the task at the specified index as done in the TaskList,
     * saves the updated task list to storage, and displays a confirmation message.
     * @param tasks The TaskList to operate on.
     * @param ui The Ui to display messages to the user.
     * @param storage The Storage to save the updated task list.
     * @throws SageException If the task index is invalid.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Ui ui, Storage storage) throws SageException {
        tasks.markTask(taskIndex);
        storage.save(tasks.getTasks());
        return ui.showMessageAndReturn("Nice! I've marked this task as done:\n   " + tasks.getTask(taskIndex));
    }
}
