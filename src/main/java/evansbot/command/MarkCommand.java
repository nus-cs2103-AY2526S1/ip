package evansbot.command;

import evansbot.Exceptions.InvalidTaskIndexException;
import evansbot.task.Storage;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

/**
 * Represents a command to Mark a task in the TaskList.
 * When executed, this command marks the Task in the TaskList depending on its Index.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a Task in the TaskList by its Index.
     * If either the index given is 0 or invalid, throws an InvalidTaskIndexException.
     *
     * @param tasks TaskList in which the new task will be added.
     * @param ui User interface to interact with the user.
     * @param storage Storage used to save the updated task list.
     * @throws InvalidTaskIndexException If the index provided is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskIndexException {
        try {
            tasks.markTask(index);
        } catch (InvalidTaskIndexException e) {
            ui.showError("Invalid task number! Please enter a number between 1 and " + e.getMaxIndex());
        }
    }
}
