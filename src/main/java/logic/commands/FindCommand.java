package logic.commands;

import models.TaskList;
import ui.Ui;

/**
 * Represents a command to find a keyword
 */
public class FindCommand extends Command {
    private static final String FIND_ERROR_MESSAGE = "Invalid task number. Current list size is ";
    private String keyword;

    /**
     * Constructs a find command with the specified keyword
     *
     * @param keyword keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by find the task by name
     *
     * @param tasks the task list to update
     * @param ui    the user interface for displaying results
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            return ui.getFindResponse(tasks.filterTasksByKeyword(keyword));
        } catch (IndexOutOfBoundsException e) {
            return ui.getErrorResponse(FIND_ERROR_MESSAGE + tasks.size());
        }
    }
}
