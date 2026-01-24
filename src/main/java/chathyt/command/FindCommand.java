package chathyt.command;

/**
 *  Represents a command to find a task.
 */

import java.util.ArrayList;
import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     *
     * @param tasks The list of existing tasks.
     * @param storage The storage handler for saving tasks.
     * @param ui The UI handler for displaying messages.
     * @return A string message confirming the task found.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        ArrayList<Task> tasksToBeFound = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                tasksToBeFound.add(task);
            }
        }

        return Ui.showListToBeFound(tasksToBeFound);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}