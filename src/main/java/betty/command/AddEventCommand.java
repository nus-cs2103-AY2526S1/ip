package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.Event;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents a command object that adds an event
 */
public class AddEventCommand extends Command {

    private Event eventTask;
    /**
     * Construct a new command with the given event task provided
     * @param eventTask task provided to be added on command
     */
    public AddEventCommand(Event eventTask) {
        this.eventTask = eventTask;
    }

    /**
     * Executes the command to event task into task list and storage, and shows message by ui
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException {
        taskList.addEvent(this.eventTask);
        storage.store(taskList);
        return ui.addTask(this.eventTask, taskList);
    }

    /**
     * Returns whether this command should terminate the program
     * @return false as program does not terminate after this command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
