package joobot.command;
import java.util.List;

import joobot.main.JooException;
import joobot.main.Storage;
import joobot.task.Task;
import joobot.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) throws JooException {
        List<Task> all = tasks.getAllTasks();
        if (all.isEmpty()) {
            return "Your task list is empty!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < all.size(); i++) {
            sb.append(i + 1).append(". ").append(all.get(i)).append("\n");
        }
        return sb.toString().trim();
    }
}
