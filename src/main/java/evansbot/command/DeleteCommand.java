package evansbot.command;

import evansbot.Exceptions.InvalidTaskIndexException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

/**
 * Represents a command to delete a task on the task list.
 * When executed, this command deletes the specified task.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    /**
     * Executes the command to delete a specific task in TaskList.
     * If task index is invalid, throws an InvalidTaskIndexException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user (not used in this command).
     * @param storage Storage used to save the updated task list.
     * @throws InvalidTaskIndexException If task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskIndexException {
        return tasks.deleteTask(index);
    }
}

