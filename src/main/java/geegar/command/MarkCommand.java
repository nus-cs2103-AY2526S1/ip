package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.TaskList;

/**
 * A Command that marks the given task as done
 */
public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number must be positive in MarkCommand";
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList must not be null";
        assert gui != null : "Gui must not be null";
        assert storage != null : "Storage must not be null";

        tasks.markTask(taskNumber - 1);
        storage.save(tasks.getTasks());
        gui.printTaskMarked(tasks.get(taskNumber - 1));
    }
}