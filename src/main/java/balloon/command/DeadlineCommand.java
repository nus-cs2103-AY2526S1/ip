package balloon.command;

import balloon.task.Task;

/**
 * Represents a command that adds a Deadline task to the task list.
 * <p>
 * This command supports undo.
 */
public class DeadlineCommand extends AddTaskCommand {
    public DeadlineCommand(Task task) {
        super(task);
    }
}
