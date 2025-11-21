package jamal.command;

import jamal.task.TaskList;
import jamal.ui.Ui;
import jamal.util.Storage;

/**
 * Exit Command SubClass for deleting tasks
 */
public class ExitCommand extends Command {

    /**
     * Executes Command ui for exit statement
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of exit statement
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.exitStatement;
    }

    /**
     * Returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
