package balloon.command;

import balloon.task.Task;

/**
 * Represents a command that adds an Event task to the task list.
 * <p>
 * This command supports undo.
 */
public class EventCommand extends AddTaskCommand {
    public EventCommand(Task task) {
        super(task);
    }
}
