package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;


/**
 * Abstract base class for all commands in the Ryuji chatbot application.
 * <p>Each command represents an action triggered by user input. Subclasses of {@code Command}
 * implement specific behavior for different actions such as adding tasks, marking tasks as done, etc.</p>
 */
public abstract class Command {

    /** The command keyword string (e.g., "todo", "mark", "bye", "find"). */
    private final String command;

    /**
     * Constructs a {@code Command} with the specified command keyword.
     *
     * @param command the command keyword string representing the user's input
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Returns the command keyword string for this command.
     * This keyword is typically used to match the user’s input.
     *
     * @return the command string representing the user's input
     */
    public String getCommand() {
        return command;
    }

    /**
     * Indicates whether this command signals the application to exit.
     * By default, this returns {@code true} if the command string equals "bye".
     * Subclasses may override this behavior to provide different exit conditions.
     *
     * @return {@code true} if this command is an exit command (e.g., "bye"), {@code false} otherwise
     */
    public boolean isExit() {
        return this.getCommand().equals("bye");
    }

    /**
     * Executes the command, performing its intended action.
     * This method must be implemented by subclasses to define the specific action associated
     * with the command (e.g., adding a task, marking a task as done).
     *
     * @param tasks   the current {@code TaskList} containing all tasks
     * @param ui      the {@code Ui} component used to interact with the user
     * @param storage the {@code Storage} component used for managing persistent data
     * @return a message or result indicating the outcome of executing the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a string representation of this command.
     * The string is typically the command keyword (e.g., "todo", "mark", "bye").
     *
     * @return the string representation of this command keyword
     */
    @Override
    public String toString() {
        return command;
    }
}
