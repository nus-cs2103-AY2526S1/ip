package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents a command to exit the Tinkerton application.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand with the full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public ByeCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the bye command, displaying a farewell message.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException Never thrown in this implementation.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return true, as bye exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
