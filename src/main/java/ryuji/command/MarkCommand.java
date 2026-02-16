package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * <p>This command updates the task's status to "done" and provides a visual confirmation to the user.</p>
 * <p>It changes the task's state and notifies the user that the task has been marked as completed.</p>
 */
public class MarkCommand extends Command {

    /** The 1-based position of the task to be marked as done. */
    private final int position;

    /**
     * Constructs a {@code MarkCommand} with the specified command string and task position.
     * The position is a 1-based index representing the task's position in the list.
     *
     * @param command  the command keyword (typically "mark")
     * @param position the position of the task to mark in the task list (1-based index)
     * @throws AssertionError if the position is less than or equal to 0
     */
    public MarkCommand(String command, int position) {
        super(command);
        assert position > 0 : "Position must be greater than 0";
        this.position = position;
    }

    /**
     * Executes the mark command by updating the status of the specified task.
     * This method changes the task's status to "done" and returns a confirmation message.
     *
     * @param tasks   the current {@code TaskList} containing all tasks
     * @param ui      the {@code Ui} used for displaying messages (not used in this command)
     * @param storage the {@code Storage} responsible for saving task data (not used in this command)
     * @return a message indicating that the task has been marked as done
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = tasks.mark(position);
        try {
            storage.updateTaskStatus(position, tasks.getTask(position));
        } catch (Exception e) {
            return "There was an error trying to mark your task master: " + e.getMessage();
        }
        return message;
    }
}
