package panda.action;

import panda.FileManager;
import panda.PandaUi;
import panda.TaskList;

/**
 * ExitAction is a subclass of Action, and handles the action
 * of ending the program.
 */
public class ExitAction extends Action {
    public ExitAction() {
    }

    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) {
        ui.bye();
    }

    /**
     * Returns true so that program will terminate.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }
}
