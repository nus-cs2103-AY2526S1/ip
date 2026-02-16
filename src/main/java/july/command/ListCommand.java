package july.command;

import java.util.ArrayList;

import july.data.Storage;
import july.tasks.Task;
import july.ui.Ui;

/**
 * Command to display all tasks in the current task list.
 * This command shows the total number of tasks and lists each task
 * with its corresponding index number for user reference.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks in the task list.
     * Shows the total task count and prints each task with its numbered index
     * (starting from 1) for easy identification by the user.
     *
     * @param tasks the ArrayList of tasks to display
     * @param ui the user interface component for displaying output
     * @param storage the storage component for data persistence
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ArrayList<String> taskStrings = new ArrayList<>();
        taskStrings.add(String.format("You currently have %d task in your list%n", tasks.size()));

        for (int i = 0; i < tasks.size(); i++) {
            taskStrings.add(String.format("%d.%s", i + 1, tasks.get(i)));
        }
        addResponses(taskStrings.toArray(new String[0]));
    }
}
