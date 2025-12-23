package scribbles.command;

import scribbles.Scribbles;
import scribbles.storage.Storage;
import scribbles.task.Task;
import scribbles.tasklist.TaskList;

/**
 * Provides the command logic to find tasks based on keyword.
 */
public class FindCommand extends Command {

    private final String findStr;

    /**
     * Constructs a command to find tasks based on keyword.
     *
     * @param findStr String to find in tasks.
     */
    public FindCommand(String findStr) {
        this.findStr = findStr.toLowerCase();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Scribbles scribbles, TaskList taskList, Storage storage) {
        StringBuilder filteredTasks = new StringBuilder();
        int taskNum = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            boolean isContainedInTask = task.getDescription().toLowerCase().contains(this.findStr);
            if (isContainedInTask) {
                taskNum += 1;
                filteredTasks.append("    %s. %s\n".formatted(taskNum, task));
            }
        }

        if (filteredTasks.isEmpty()) {
            return "I can't find any task with '%s' in it.. -.-".formatted(this.findStr);
        } else {
            return filteredTasks.toString();
        }
    }
}
