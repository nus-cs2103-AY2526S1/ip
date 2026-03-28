package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        ui.showListMessage(taskList);
        return false;
    }
}
