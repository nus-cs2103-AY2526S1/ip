package marvin.command;

import marvin.MarvinException;
import marvin.Personality;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for the delete command in Marvin.
 */
public class DeleteTaskCommand extends Command {
    private final String locator;

    /**
     * Instantiate a delete task command.
     *
     * @param locator The string representation of where the task to be deleted is.
     */
    public DeleteTaskCommand(String locator) {
        this.locator = locator;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        // Remove the old task from the list
        String oldTask;
        try {
            oldTask = taskList.removeTask(locator);
        } catch (MarvinException e) {
            return new CommandResult(() -> Ui.printGeneric(e.getMessage()), e.getMessage());
        }

        // Return the removed text
        String reply = Personality.getTaskRemovedText(oldTask, taskList.getCount());
        return new CommandResult(() -> Ui.printGeneric(reply), reply);
    }
}
