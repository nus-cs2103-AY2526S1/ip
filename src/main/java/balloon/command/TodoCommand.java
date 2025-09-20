package balloon.command;

import balloon.task.Task;

/**
 * Represents a command that adds a Todo task to the task list.
 * <p>
 * This command supports undo.
 */
public class TodoCommand extends AddTaskCommand {
    public TodoCommand(Task task) {
        super(task);
    }
}
