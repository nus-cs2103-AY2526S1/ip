package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.TaskList;

/**
 * A Command that unmarks the task as not done
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        assert taskNumber > 0 : "Task number must be positive in UnmarkCommand";
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList must not be null";
        assert gui != null : "Gui must not be null";
        assert storage != null : "Storage must not be null";

        tasks.unmarkTask(taskNumber - 1);
        storage.save(tasks.getTasks());
        gui.printTaskUnmarked(tasks.get(taskNumber - 1));
    }

}
