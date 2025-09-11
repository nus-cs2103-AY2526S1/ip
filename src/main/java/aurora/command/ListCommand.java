package aurora.command;

import aurora.task.TaskList;

/**
 * Command to display all tasks in the list.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList list) {
        if (list.isEmpty()) {
            return "Your list is empty.";
        }

        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("\n%d. %s", i + 1, list.get(i)));
        }

        return result.toString();
    }
}
