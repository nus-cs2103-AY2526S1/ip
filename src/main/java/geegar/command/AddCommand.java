package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;

/**
 * A Command that adds a task to the list
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Taskes in the tasks and Creates an instance of a AddCommand object
     * @param task
     */
    public AddCommand(Task task) {
        assert task != null : "Task in AddCommand must not be null";
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList in AddCommand must not be null";
        assert gui != null : "Gui in AddCommand must not be null";
        assert storage != null : "Storage in AddCommand must not be null";

        tasks.add(task);
        storage.save(tasks.getTasks());
        gui.printTaskAdded(task, tasks.size());
    }
}
