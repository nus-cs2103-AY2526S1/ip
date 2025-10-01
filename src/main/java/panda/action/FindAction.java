package panda.action;

import panda.FileManager;
import panda.PandaUi;
import panda.TaskList;

/**
 * FindAction is a subclass of Action, which handles the action
 * of listing the tasks with matching keyword to the user.
 */
public class FindAction extends Action {
    private final String keyword;

    /**
     * Constructs FindAction object with the provided keyword.
     *
     * @param keyword Keyword provided by user.
     */
    public FindAction(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) {
        ui.listKeywordTasks(tasks, keyword);
    }

    public boolean isExit() {
        return false;
    }
}
