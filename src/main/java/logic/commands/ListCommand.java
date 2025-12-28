package logic.commands;

import models.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks
     *
     * @param tasks the task list to display
     * @param ui    the user interface for displaying tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.getListResponse(tasks);
    }
}
