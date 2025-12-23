package george.command;

import george.task.TaskManager;

/**
 * Represents a command to list all tasks in the task manager.
 * This command displays all currently stored tasks to the user.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskManager manager) {
        return manager.listTasks();
    }

    @Override
    public String getCommandWord() {
        return "list";
    }
}
