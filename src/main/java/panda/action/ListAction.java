package panda.action;

import panda.FileManager;
import panda.PandaUi;
import panda.TaskList;

/**
 * ListAction is a subclass of Action, and handles the action
 * of displaying the list of task to user.
 */
public class ListAction extends Action {
    public ListAction() {
    }

    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) {
        ui.list(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
