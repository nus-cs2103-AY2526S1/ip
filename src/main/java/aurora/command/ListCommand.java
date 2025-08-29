package aurora.command;
import aurora.task.Task;
import java.util.List;

/**
 * Command to display all tasks in the list.
 */
public class ListCommand implements Command {
    @Override
    public String execute(List<Task> list) {
        if (list.isEmpty()) {
            return "Your list is empty.";
        }

        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, list.get(i)));
        }

        return result.toString();
    }
}
