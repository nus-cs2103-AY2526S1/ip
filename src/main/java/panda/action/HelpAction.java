package panda.action;

import panda.FileManager;
import panda.PandaUi;
import panda.TaskList;

/**
 * HelpAction is a subclass of Action, and handles the action
 * of displaying the list of actions to user.
 */
public class HelpAction extends Action {
    public HelpAction() {
    }

    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) {
        ui.showHelp();
    }

    public boolean isExit() {
        return false;
    }
}
