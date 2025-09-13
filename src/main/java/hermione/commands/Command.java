package hermione.commands;

import hermione.storage.TaskStorage;

/**
 * Represents a command in the Hermione application.
 */
public abstract class Command {

    protected final TaskStorage storage;
    protected final String argument;

    /**
     * Constructor for the Command class.
     *
     * @param storage  The TaskStorage instance used to manage tasks.
     * @param argument The argument string that contains the command details.
     */
    public Command(TaskStorage storage, String argument) {
        assert storage != null : "TaskStorage cannot be null";
        assert argument != null : "Argument cannot be null";

        this.storage = storage;
        this.argument = argument;
    }

    /**
     * Executes the command and returns a result string.
     * This method must be implemented by subclasses to define specific command
     * behavior.
     *
     * @return A string result of the command execution.
     */
    public abstract String execute();
}
