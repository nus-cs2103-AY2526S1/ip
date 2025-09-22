
package broccoli.Command;

import broccoli.Tasks.Task;
import java.util.ArrayList;
import java.util.stream.Collectors;
import broccoli.Storage;
import broccoli.TaskList;
import broccoli.Ui;


public class FindCommand extends Command {
    private final String keyWords;

    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }


    /**
     * Executes the find command to search for matching tasks.
     * Filters the task list to find all tasks whose descriptions contain
     * the specified keywords. If matches are found, displays them in a
     * formatted list. If no matches are found, returns an error message.
     *
     * @param taskList The task list to search through
     * @param ui The user interface for displaying messages
     * @param storage The storage system (not modified by this command)
     * @return A formatted list of matching tasks with their descriptions,
     *         or an error message if no matching tasks are found
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> taskList1 = (ArrayList<Task>) taskList.getList().stream()
                    .filter(task -> task.getDescription().contains(keyWords))
                    .collect(Collectors.toList());
            if (taskList1.isEmpty()) {
                throw new IllegalArgumentException("There is not matching tasks");
            } else {
                TaskList matchedTasks = new TaskList(taskList1);
                String output_2 = matchedTasks.displayTask();
                String output = "Here are the matching tasks in your list:\n" + output_2;
                return output;
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
