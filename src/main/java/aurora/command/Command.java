package aurora.command;

import aurora.task.TaskList;

/**
 * User command that can be executed by the chatbot.
 * All commands implement this interface and define their own
 * behavior in the {@link #execute(TaskList)} method.
 */
public interface Command {

    /**
     * Executes command on the given list of tasks.
     *
     * @param list list of tasks
     */
    public String execute(TaskList list);
}
