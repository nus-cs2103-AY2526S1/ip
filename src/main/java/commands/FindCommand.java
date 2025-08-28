package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching tasks for the keyword
     * and displaying matching tasks.
     *
     * @param tasks   the task list
     * @param ui      the user interface
     * @param storage the storage handler
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> allTasks = tasks.getAllTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showError("oh no!!! no matching tasks found for: " + keyword);
            return;
        }

        ui.showLine();
        System.out.println("here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
        }
        ui.showLine();
    }

    /**
     * The find command never exits the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

