package mochibot.command;

import mochibot.MochiBotException;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

/**
 * <p>
 *     This class represents a Mark command, providing the method {@code execute}
 *     to mark a task in the task list.
 * </p>
 * <p>
 *     The constructor takes in a {@code taskIndex} to indicate the task being marked.
 * </p>
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the {@code MarkCommand} object to mark a task in the task list,
     * saves the task list to the local text file, and displays message indicating
     * task being marked.
     *
     * @param tasks {@link TaskList} object to stores all tasks.
     * @param gui {@link Gui} object to print displays.
     * @throws MochiBotException If taskIndex >= size of task list.
     */
    @Override
    public String execute(TaskList tasks, Gui gui) throws MochiBotException {
        assert taskIndex >= 0 : "taskIndex cannot be negative";
        if (this.taskIndex >= tasks.getSize()) {
            throw new MochiBotException.InvalidTaskIndexException();
        }
        Task task = tasks.getTask(this.taskIndex);
        if (task.isDone()) {
            throw new MochiBotException.DuplicateMarkTaskException();
        }
        task.markDone();
        Storage.saveTaskList(tasks);
        return gui.displayMarkTask(task);
    }
}
