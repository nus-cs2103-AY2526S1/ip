package tony.commands;

import tony.exceptions.InvalidIndexException;
import tony.exceptions.TonyException;
import tony.storage.Storage;
import tony.tasks.Task;
import tony.tasks.TaskList;
import tony.ui.UI;

/**
 * Represents a command to mark a {@link Task} from the task list.
 * The user specifies the task to mark by its 1-based index in the list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a new {@link MarkCommand} by parsing the input arguments
     *
     * @param args The raw input string containing the task index.
     * @throws TonyException If the input does not contain an integer.
     */
    public MarkCommand(String args) throws TonyException {
        try {
            this.index = Integer.parseInt(args.trim());
        } catch (NumberFormatException e) {
            throw new TonyException("JARVIS, show them how it's done.\n\tmark <number>");
        }
    }

    /**
     * Executes the {@code MarkCommand}.
     * Checks that the index is within bounds of the {@link TaskList}.
     * Marks the task at the given index.
     * Saves the updated task list to persistent storage via {@link Storage}.
     * Displays confirmation of the deleted task through the {@link UI}.
     *
     * @param tasks The {@link TaskList} from which the task will be marked.
     * @param ui The {@link UI} instance for displaying feedback to the user.
     * @param storage The {@link Storage} instance for saving tasks to file.
     * @return The {@link Task} that has been marked as a {@link String}.
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
                tasks.markDone(index);
                storage.save(tasks);
                return ui.showMark(task);
            }
        } catch (TonyException e) {
            return ui.showError(e.getMessage());
        }
    }
}
