package command;

import taskmodule.TaskList;
import ui.Penny;

/**
 * Represents an abstract user command in the Penny application.
 *
 * <p>A {@code Command} encapsulates an instruction from the user.
 * It may query or modify the global {@link TaskList}, and produces
 * a response message that is displayed to the user.</p>
 *
 * <p>Concrete subclasses define the specific behavior of each command
 * (e.g., adding, deleting, or marking tasks). The {@link #respond()}
 * method should be implemented to perform the required action and
 * return an appropriate message.</p>
 */
public abstract class Command {
    public static final TaskList taskList = Penny.storage.getTaskList();
    public static boolean shouldExit = false;

    /**
     * Executes this command and returns Penny's response message.
     *
     * <p>The implementation may modify the global {@link TaskList}
     * if the command involves changing stored tasks (e.g., adding
     * or deleting). Commands that do not alter the task list
     * (e.g., listing tasks) should still return a suitable message
     * for the user.</p>
     *
     * @return the message to be displayed to the user
     */
    public abstract String respond();
}
