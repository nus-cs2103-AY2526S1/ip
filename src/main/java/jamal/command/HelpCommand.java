package jamal.command;

import jamal.task.TaskList;
import jamal.ui.Ui;
import jamal.util.Storage;

/**
 * Help Command SubClass for listing commands
 */
public class HelpCommand extends Command {

    /**
     * Executes Command ui for exit statement
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of commands
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.showHelplist();
    }
}
