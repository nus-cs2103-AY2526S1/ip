package som.command;

import som.SomException;
import som.TaskList;
import som.Ui;

/**
 * Defines the interface for executable commands in the Som chatbot.
 * <p>This abstract class ensures that all command objects can be executed
 * uniformly by the main application loop. Each concrete command must
 * implement the execute method to perform its specific action and indicate
 * whether it triggers application exit.</p>
 *
 * @author Darien Tan
 */
public abstract class Command {
    /**
     * Executes the command using the given TaskList and Ui.
     * <p>This method carries out the command's intended behavior, such as
     * adding, marking or deleting tasks. It may modify the task list
     * and/or produce results via the Ui. If the command fails, it
     * throws a SomException. </p>
     *
     * @param tasks the task list on which the command operates.
     * @param ui the user interface used to display results or errors.
     * @return string representing the operation.
     * @throws SomException if the command fails due to invalid input or state.
     */
    public abstract String execute(TaskList tasks, Ui ui) throws SomException;

    /**
     * Indicates whether this command should cause the application to exit.
     *
     * @return true if executing this command should terminate the app, false otherwise.
     */
    public abstract boolean isExit();
}
