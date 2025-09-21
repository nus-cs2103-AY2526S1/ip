package itachi.command;

import java.util.ArrayList;

import itachi.Storage;
import itachi.Ui;
import itachi.task.Task;
import itachi.task.TaskList;

/**
 * Represents a command that searches for tasks in the task list
 * containing a specified keyword. The search is case-sensitive
 * and matches any task description that contains the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list should never be null";
        ArrayList<Task> allTasks = tasks.getTasks();
        ArrayList<Task> matched = new ArrayList<>();

        for (Task t : allTasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matched.add(t);
            }
        }

        if (matched.isEmpty()) {
            ui.showMessage("No matching tasks found for: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks:");
            for (int i = 0; i < matched.size(); i++) {
                ui.showMessage((i + 1) + ". " + matched.get(i).toString());
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }
}
