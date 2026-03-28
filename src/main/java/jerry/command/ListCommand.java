package jerry.command;

import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * Retrieves all the tasks from the task list, formatting them into a
 * readable string and display them to the user.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Task list should not be empty";
        this.response = taskList.getList();
        ui.displayOutput(this.response);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getString() {
        return this.response;
    }
}
