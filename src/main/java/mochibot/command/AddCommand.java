package mochibot.command;

import mochibot.MochiBotException;
import mochibot.storage.Storage;
import mochibot.task.Task;
import mochibot.task.TaskList;
import mochibot.ui.Gui;

/**
 * <p>
 *     This class represents an Add command, providing the method {@code execute}
 *     to add tasks into the task list.
 * </p>
 * <p>
 *     The constructor for the {@code AddCommand} class takes in a {@link Task} object.
 * </p>
 */
public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the {@code AddCommand} object to add tasks to the task list,
     * saves the task list to local text file, and displays message
     * indicating task being added.
     *
     * @param tasks {@link TaskList} object that stores all tasks.
     * @param gui {@link Gui} object to print displays.
     */
    @Override
    public String execute(TaskList tasks, Gui gui) throws MochiBotException {
        assert tasks != null : "task cannot be null";
        if (isDuplicate(tasks, this.task)) {
            throw new MochiBotException.DuplicateTaskException();
        }
        tasks.addTask(this.task);
        Storage.saveTaskList(tasks);
        return gui.displayAddTask(this.task, tasks);
    }

    private static boolean isDuplicate(TaskList tasks, Task task) {
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDescription().equals(task.getDescription())) {
                return true;
            }
        }
        return false;
    }
}
