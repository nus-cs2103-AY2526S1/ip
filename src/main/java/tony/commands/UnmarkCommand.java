package tony.commands;

import tony.exceptions.InvalidIndexException;
import tony.exceptions.TonyException;
import tony.storage.Storage;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to unmark a {@link Task} from the task list.
 * The user specifies the task to unmark by its 1-based index in the list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs a new {@link UnmarkCommand} by parsing the input arguments
     *
     * @param args The raw input string containing the task index.
     * @throws TonyException If the input does not contain an integer.
     */
    public UnmarkCommand(String args) throws TonyException {
        try {
            this.index = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new TonyException("JARVIS, show them how it's done.\n\tunmark <number>");
        }
    }

    /**
     * Executes the {@code UnmarkCommand}.
     * Checks that the index is within bounds of the {@link TaskList}.
     * Marks the task at the given index.
     * Saves the updated task list to persistent storage via {@link Storage}.
     * Displays confirmation of the deleted task through the {@link UI}.
     *
     * @param tasks The {@link TaskList} from which the task will be unmarked.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The {@link Task} that has been unmarked as a {@link String}.
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
                Task task = tasks.getTask(index);
                tasks.markUndone(index);
                storage.save(tasks);
                return ui.showUnmark(task);
            }
        } catch (TonyException e) {
            return ui.showError(e.getMessage());
        }
    }
}
