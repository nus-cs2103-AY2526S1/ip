package geegar.command;

import java.util.ArrayList;

import geegar.exception.GeegarException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.Task;
import geegar.task.TaskList;

/**
 * This command allows to find a Task Object based on the given keyword input
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Keyword cannot be null or empty";
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {

        assert tasks != null : "TaskList must not be null";
        assert gui != null : "Gui must not be null";
        assert storage != null : "Storage must not be null";

        gui.printFind();
        ArrayList<Task> tasksOnKeyword = tasks.showTasksOnKeyword(keyword);
        for (Task task : tasksOnKeyword) {
            gui.printTask(task);
        }
        if (tasksOnKeyword.size() == 0) {
            gui.printEmpty();
        }
    }

}
