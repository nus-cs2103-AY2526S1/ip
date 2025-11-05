package sunoo.command;

import sunoo.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command and instructs the Ui to put messages of to be shown together and returns it.
     *
     * @param tasks The list of current tasks.
     * @return String to be shown.
     */
    public abstract String execute(TaskList tasks);

    /**
     * Returns instruction of whether chatbot should exit after execution of command.
     *
     * @return True if chatbot Sunoo should exit.
     */
    public abstract boolean shouldExit();
}
