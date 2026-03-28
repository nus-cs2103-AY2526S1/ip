package jerry.command;

import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

/**
 * When executed, this command saves the current task to storage,
 * displays a farewell message to the user and the application will terminate.
 */

public class ByeCommand extends Command {

    public String end() {
        return "Bye! See you next time :D";
    }

    /**
     * Executes the bye command by saving tasks to storage and displaying a farewell message.
     *
     * @param taskList the task list to operate on.
     * @param ui       the user interface for input/output.
     * @param storage  the storage system to save or load tasks.
     * @throws JerryException handles if there is an error while saving tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JerryException {
        taskList.saveTasks(storage);
        assert taskList != null : "Task list should not be empty in the storage file";
        ui.displayOutput(this.end());
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getString() {
        this.response = this.end();
        return this.response;
    }
}
