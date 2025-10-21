package geegar.command;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.TaskList;

/**
 * A Command that lists all the tasks
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList must not be null";
        assert gui != null : "Gui must not be null";
        assert storage != null : "Storage must not be null";

        gui.printTaskList(tasks);
    }
}
