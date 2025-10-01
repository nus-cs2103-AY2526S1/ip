package panda.action;

import panda.FileManager;
import panda.PandaException;
import panda.PandaUi;
import panda.TaskList;

/**
 * DeleteAction is a subclass of Action, and handles the action
 * of deleting a task.
 */
public class DeleteAction extends Action {
    private final String number;
    public DeleteAction(String number) {
        this.number = number;
    }

    /**
     * Deletes task from the list, then informs user.
     * Text file is also updated.
     *
     * @param tasks List to delete task from.
     * @param ui User Interface to communicate with user.
     * @param fileManager File manager to save the tasks to text file.
     * @throws PandaException If String number provided in not an integer or out of array index.
     */
    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) throws PandaException {
        ui.showTaskDeleted(tasks.deleteTask(number), tasks);
        fileManager.saveTasks(tasks, ui);
    }

    public boolean isExit() {
        return false;
    }
}
