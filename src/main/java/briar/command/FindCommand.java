package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that finds tasks with the input keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a command to find tasks with descriptions that contain a keyword.
     * @param keyword String to look for in task descriptions.
     */
    public FindCommand(String keyword) {
        super();
        assert keyword != null : "taskToAdd should not be null";
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        String taskListString = tasks.find(keyword);
        return ui.showFoundTasks(taskListString);
    }

    /**
     * Returns a String containing the format of the command and what it does.
     */
    public static String getCommandInformation() {
        return "find <keyword> | Find tasks that contain the specified keyword";
    }

    @Override
    public String toString() {
        return "Find: " + keyword;
    }
}
