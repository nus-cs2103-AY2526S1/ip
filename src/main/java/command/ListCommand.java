package command;
import task.Task;
import java.util.List;

public class ListCommand implements Command {
    @Override
    public String execute(List<Task> list) {
        if (list.isEmpty()) {
            return "Your list is empty.";
        }

        StringBuilder result = new StringBuilder("Aurora: Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            result.append(String.format("%d. %s", i + 1, list.get(i)));
        }

        return result.toString();
    }
}
