package logic.commands;

import models.TaskList;
import storage.FileManager;
import ui.Ui;

/**
 * Represents a command to mark or unmark a task
 */
public class MarkCommand extends Command {
    private static final String MARK_ERROR_MESSAGE = "Invalid task number. Current list size is ";
    private int index;
    private boolean isDone;

    /**
     * Constructs a mark command with the specified task index and mark status
     *
     * @param index  the index of the task to mark/unmark
     * @param isDone true to mark as done, false to unmark
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the mark command by updating the specified task's status
     *
     * @param tasks the task list to update
     * @param ui    the user interface for displaying results
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            tasks.markTask(index - 1, isDone);
            FileManager.saveTasks(tasks.getTasks());
            return ui.getMarkTaskResponse(tasks.get(index - 1), isDone);
        } catch (IndexOutOfBoundsException e) {
            return ui.getErrorResponse(MARK_ERROR_MESSAGE + tasks.size());
        }
    }
}
