package dwight.command;

import dwight.task.TaskList;

/**
 * Represents an abstract command that can be executed on a task list with an optional description.
 * Concrete subclasses must implement the execution logic.
 */
public abstract class Command {

    /**
     * Executes the command on the specified task list using the given description.
     *
     * @param list The task list on which the command operates.
     * @param description Additional details required to execute the command.
     * @return A string message describing the result of executing the command.
     */
    public abstract CommandResponse execute(TaskList list, String description);
}
