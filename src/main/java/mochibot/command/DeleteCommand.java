package mochibot.command;

import mochibot.MochiBotException;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

/**
 * <p>
 *     This class represents a Delete command, providing the method {@code execute}
 *     to delete a task from the task list.
 * </p>
 * <p>
 *     The constructor takes in a {@code taskIndex} to indicate the task being deleted.
 * </p>
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the {@code DeleteCommand} object to delete a task from the task list,
     * saves the task list to the local text file, and displays message indicating
     * task deletion.
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
        Task task = tasks.removeTask(this.taskIndex);
        Storage.saveTaskList(tasks);
        return gui.displayRemoveTask(task, tasks);
    }
}
