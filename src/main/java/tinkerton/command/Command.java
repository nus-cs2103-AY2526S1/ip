package tinkerton.command;

import tinkerton.util.Ui;
import tinkerton.core.TinkertonException;
import tinkerton.task.TaskList;
import tinkerton.storage.Save;

/**
 * Represents an abstract command that can be executed in the Tinkerton application. Subclasses must
 * implement the execution logic and exit condition.
 */
public abstract class Command {
    /** The full user input command string. */
    private String fullCommand;

    /**
     * Constructs a Command with the specified full command string.
     *
     * @param fullCommand The full user input command string.
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Returns the full user input command string.
     *
     * @return The full command string.
     */
    public String getFull() {
        return fullCommand;
    }

    /**
     * Executes the command with the given task list, user interface, and save handler.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface handler.
     * @param save The save handler for persisting tasks.
     * @throws TinkertonException If the command execution fails.
     * @return The farewell message.
     */
    public abstract String execute(TaskList tasks, Ui ui, Save save) throws TinkertonException;

    /**
     * Indicates whether this command should exit the application.
     *
     * @return True if the command should exit, false otherwise.
     */
    public abstract boolean isExit();
}
