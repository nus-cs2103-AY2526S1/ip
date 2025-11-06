package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.ui.Ui;

/**
 * Represents a command to list all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command and calls the ui object to list all current tasks.
     *
     * @param tasks The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTasks().isEmpty()) {
            return ("You currently do not have any tasks");
        } else {
            assert !tasks.getTasks().isEmpty() : "list should not be empty";
            return "Here are the tasks in your list:" + ui.showTasks(tasks.getTasks());
        }
    }
}
