package panda.action;

import panda.FileManager;
import panda.PandaException;
import panda.PandaUi;
import panda.TaskList;

/**
 * MarkAction is a subclass of Action, and handles the action
 * of marking or unmarking a task.
 */
public class MarkAction extends Action {
    private final String number;
    private final String action;

    /**
     * Constructs MarkAction object with the number and action.
     *
     * @param number Index of task in list.
     * @param action Unmark or mark.
     */
    public MarkAction(String number, String action) {
        this.number = number;
        this.action = action;
    }

    /**
     * Changes the task status of a task in the list, then informs user.
     *
     * @param tasks List to mark task.
     * @param ui User Interface to communicate with user.
     * @param fileManager File manager to save the tasks to text file.
     * @throws PandaException If String number provided in not an integer or out of array index.
     */
    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) throws PandaException {
        if (action.equals("mark")) {
            ui.showTaskMarked(tasks.changeStatus(number, action));
        } else if (action.equals("unmark")) {
            ui.showTaskUnmarked(tasks.changeStatus(number, action));
        }
        fileManager.saveTasks(tasks, ui);
    }

    public boolean isExit() {
        return false;
    }
}
