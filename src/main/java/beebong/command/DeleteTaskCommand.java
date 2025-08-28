package beebong.command;

import beebong.exception.BBongException;
import beebong.exception.InvalidTaskDetailsException;
import beebong.storage.Storage;
import beebong.task.Task;
import beebong.task.TaskList;
import beebong.ui.UI;

/**
 * Represents a Command that deletes a task from the task list.
 *
 * <p>This command removes the task at the specified index if it exists,
 * and informs the user through the UI. If the task number is invalid,
 * an exception is thrown.</p>
 */
public class DeleteTaskCommand extends Command {
    private final int taskNum;

    /**
     * Creates a new {@code DeleteTaskCommand} with the specified task number.
     *
     * @param taskNum the index of the task to be deleted (0-indexed).
     */
    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
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
        // Delete Task
        Task removedTask = taskList.deleteTask(taskNum);
        ui.showMessage("Bing! This task has been removed:\n"
                + removedTask + "\nYou now have " + taskList.length()
                + " task(s) buzzing around in the list.");
    }
}
