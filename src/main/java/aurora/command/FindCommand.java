package aurora.command;

import aurora.task.Task;
import aurora.task.TaskList;

/**
 * Command to find matching tasks from task list with given String input.
 */
public class FindCommand implements Command {
    private final String search;

    /**
     * Creates a new {@code FindCommand}.
     *
     * @param search String to search the task list.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public String execute(TaskList list) {
        if (list.isEmpty()) {
            return "Your list is empty.";
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");

        int index = 1;
        for (Task task : list.getTasks()) {
            if (task.getDescription().contains(search)) {
                result.append(String.format("%d.%s", index, task));
                index++;
            }
        }

        return result.toString();
    }
}
