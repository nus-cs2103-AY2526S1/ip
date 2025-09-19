package eureka.command;

import eureka.Storage;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents an abstract command that can be executed by the application.
 * <p>
 * Each command defines specific behavior (e.g., add, delete, find tasks)
 * and interacts with the {@link TaskList}, {@link Ui}, and {@link Storage}.
 * Subclasses must implement how the command executes and whether it
 * terminates the program.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   the task list to operate on
     * @param ui      the user interface used to display messages
     * @param storage the storage used for saving/loading tasks
     * @return a string response representing the command result,
     *         mainly used for GUI display
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether this command signals the application to exit.
     *
     * @return true if this command will terminate the program, false otherwise
     */
    public abstract boolean isExit();
}
