package logic.commands;

import models.TaskList;
import storage.FileManager;
import ui.Ui;

/**
 * Represents an exit command to terminate the application
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by saving tasks and displaying exit message
     *
     * @param tasks the task list to save before exiting
     * @param ui    the user interface for displaying exit message
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        FileManager.saveTasks(tasks.getTasks());
        return ui.getExitResponse();
    }
}
