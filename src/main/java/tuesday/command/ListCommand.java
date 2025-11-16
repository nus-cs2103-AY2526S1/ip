package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

/**
 * Represent a command to list all tasks currently in the task list
 * Print each task with its index to the console
 */
public class ListCommand extends Command {
    private static final String NO_TASK_FOUND = "No tasks found";
    private static final String TASKS_FOUND = "Here are the tasks in your list:";
    /**
     * Execute the list command by printing all tasks in the task list
     * along with their indices
     * @param tasks: List of Tasks
     * @param ui: UI
     * @param storage: Storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = getResponse(tasks, ui, storage);
        System.out.println(response);
    }

    /**
     * Return the response for the List command
     * @param tasks: List of Tasks
     * @param ui: UI
     * @param storage: Storage
     * @return String of response
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        String response = "";
        if (tasks.getTasks().isEmpty()) {
            response = NO_TASK_FOUND;
            System.out.println(response);
        } else {
            System.out.println(TASKS_FOUND);
            for (int i = 0; i < tasks.size(); i++) {
                response += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
                System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
        return response;
    }
    /**
     * Indicate whether this command should exit
     * @return Always false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
