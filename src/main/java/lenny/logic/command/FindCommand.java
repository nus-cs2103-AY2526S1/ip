package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;


/**
 * Represents a command that searches for tasks in the task list
 * whose names contain a given keyword (case-insensitive).
 * Returns a list of matching tasks formatted for display.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param s The keyword to search for within task names.
     */
    public FindCommand(String s) {
        this.keyword = s;
    }

    /**
     * Executes the find command by searching through the task list
     * for tasks that contain the keyword (ignoring case). The
     * resulting matches are formatted as a numbered list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param storage The Storage object responsible for persisting tasks
     *                (not directly used here).
     * @param ui      The Ui component used for interactions
     *                (not directly used here).
     * @return A string containing all tasks that match the keyword,
     *         formatted as a numbered list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        StringBuilder response = new StringBuilder("\uD83D\uDD0D Detective mode activated... here’s what I dug up:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i).getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
                response.append((i)).append(". ").append(tasks.get(i)).append("\n");
                response.append("This task has priority level: ").append(tasks.get(i).getPriority()).append("\n");
            }
        }
        return response.toString();
    }
}
