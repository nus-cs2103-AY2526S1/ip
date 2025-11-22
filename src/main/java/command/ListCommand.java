package command;

import java.util.List;

import exception.GenieweenieException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GenieweenieException {
        List<Task> taskList = tasks.getTasks();
        if (taskList.isEmpty()) {
            response = "No tasks in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                sb.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            response = sb.toString().trim();
        }
        return response;
    }
}
