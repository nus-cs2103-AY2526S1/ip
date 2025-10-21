package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;

/**
 * A Command that Deletes a task from the list
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates an instance of a DeleteCommand using the input taskNumber
     * @param taskNumber
     */
    public DeleteCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number must be positive";
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList in DeleteCommand must not be null";
        assert gui != null : "Gui in DeleteCommand must not be null";
        assert storage != null : "Storage in DeleteCommand must not be null";

        Task deletedTask = tasks.delete(taskNumber - 1);
        storage.save(tasks.getTasks());
        gui.printTaskDeleted(deletedTask, tasks.size());
    }
}
