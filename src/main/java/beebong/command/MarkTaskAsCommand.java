package beebong.command;

import beebong.exception.BBongException;
import beebong.exception.InvalidTaskDetailsException;
import beebong.storage.Storage;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents a Command that marks a task as either complete or incomplete.
 *
 * <p>This command updates the completion status of the task at the specified
 * index and notifies the user through the UI. If the task number is invalid,
 * an exception is thrown.</p>
 */
public class MarkTaskAsCommand extends Command {
    private final int taskNum;
    private final boolean isComplete;

    /**
     * Creates a new {@code MarkTaskAsCommand} with the specified task number and status.
     *
     * @param taskNum    the index of the task to be marked (0-indexed).
     * @param isComplete {@code true} if the task should be marked as complete,
     *                   {@code false} if it should be marked as incomplete.
     */
    public MarkTaskAsCommand(int taskNum, boolean isComplete) {
        this.taskNum = taskNum;
        this.isComplete = isComplete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws BBongException {
        // Check for valid task number
        if (taskNum < 0 || taskNum >= taskList.length()) {
            throw new InvalidTaskDetailsException("That task number doesn’t exist. Try a real one!");
        }
        // Mark Task as Completed/Incomplete
        taskList.markTaskAs(taskNum, isComplete);
        ui.showMessage("Bing! Task #" + (taskNum + 1) + " marked as "
                + ((isComplete) ? "complete" : "incomplete") + "!");
    }
}
