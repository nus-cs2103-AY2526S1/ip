package scribbles.command;

import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.task.Task;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to list all tasks in taskList.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        if (taskList.size() == 0) {
            return "Your task list is currently empty :3";
        }

        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            tasks.append("    %s. %s\n".formatted(i + 1, task));
        }
        return tasks.toString();
    }
}
