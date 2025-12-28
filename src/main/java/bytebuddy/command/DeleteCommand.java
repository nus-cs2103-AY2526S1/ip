package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 * <p>
 * The {@code DeleteCommand} takes in a user-provided task index as a string,
 * validates it, and removes the corresponding task if valid.
 * It returns appropriate feedback messages via the {@link Ui}.
 * </p>
 */
public class DeleteCommand extends Command {

    /** The user input specifying the index of the task to delete. */
    private final String body;

    /**
     * Constructs a {@code DeleteCommand} with the given body.
     *
     * @param body the string input representing the task index to delete
     */
    public DeleteCommand(String body) {
        super(false);
        this.body = body;
    }

    /**
     * Executes the delete operation.
     * <p>
     * If the body is empty, invalid, or out of range, an error message is returned.
     * Otherwise, the task at the given index is removed from the {@link TaskList}.
     * </p>
     *
     * @param storage the storage manager (not modified by this command)
     * @param tasks   the current list of tasks
     * @param ui      the user interface for formatting feedback messages
     * @return a feedback message for the user about the result of the operation
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (body.isEmpty()) {
            return ui.getErrorMessage("Error: Empty body.");
        }
        try {
            int idx = Integer.parseInt(body) - 1;
            if (idx >= 0 && idx < tasks.getSize()) {
                Task removed = tasks.removeTask(idx);
                assert removed != null : "Task should not be null";
                return ui.getDeletedMessage(tasks, removed);
            } else {
                return ui.getErrorMessage("Error: Invalid task index.");
            }
        } catch (NumberFormatException e) {
            return ui.getErrorMessage("Error: Please provide a valid task number to delete.");
        }
    }
}
