package aqua.command;

import aqua.task.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the task list.
     */
    @Override
    public String execute(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "The list is currently empty.";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < taskList.size(); i++) {
            String index = String.format("%d. ", i + 1);
            sb.append(index).append(taskList.get(i)).append("\n");
        }

        return sb.toString();
    }
}
