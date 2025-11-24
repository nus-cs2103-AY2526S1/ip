package cheryl.command;

import cheryl.util.TaskList;
import cheryl.util.Ui;
import cheryl.util.Storage;
import cheryl.util.DukeException;
import cheryl.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to find tasks containing a keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the given keyword.
     */
    public FindCommand(String arguments) throws DukeException {
        if (arguments.trim().isEmpty()) {
            throw new DukeException("The search keyword cannot be empty.");
        }
        this.keyword = arguments.trim();
    }

    /**
     * Executes the command: lists all tasks containing the keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found for keyword: \"" + keyword + "\"");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
