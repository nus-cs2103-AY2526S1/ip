package tony.commands;

import tony.exceptions.InvalidIndexException;
import tony.exceptions.TonyException;
import tony.storage.Storage;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to delete a {@link Task} from the task list.
 * The user specifies the task to delete by its 1-based index in the list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a new {@link DeleteCommand} by parsing the input arguments.
     *
     * @param args The raw input string containing the task index.
     * @throws TonyException If the input does not contain an integer.
     */
    public DeleteCommand(String args) throws TonyException {
        try {
            this.index = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new TonyException("JARVIS, show them how it's done.\n\tdelete <number>");
        }
    }

    /**
     * Executes the {@code DeleteCommand}.
     * Checks that the index is within bounds of the {@link TaskList}.
     * Deletes the task at the given index.
     * Saves the updated task list to persistent storage via {@link Storage}.
     * Displays confirmation of the deleted task through the {@link UI}.
     *
     * @param tasks The {@link TaskList} from which the task will be deleted.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The {@link Task} that has been deleted as a {@link String}.
     * @throws TonyException If the index is invalid (not within list bounds).
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws TonyException {
        try {
            assert tasks != null : "TaskList cannot be null";
            assert ui != null : "UI cannot be null";
            if (this.index > tasks.getSize() || this.index < 1) {
                throw new InvalidIndexException("No offence, but do you know how to count?");
            } else {
                Task task = tasks.deleteTask(index);
                assert !tasks.getList().contains(task) : "Task has not been deleted";
                storage.save(tasks);
                return ui.showDelete(task);
            }
        } catch (TonyException e) {
            return ui.showError(e.getMessage());
        }
    }
}
