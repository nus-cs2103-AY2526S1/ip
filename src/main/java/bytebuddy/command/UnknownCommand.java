package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

/**
 * Represents a command that is executed when the user input is not recognized.
 * <p>
 * The {@code UnknownCommand} provides an error message guiding the user
 * to enter 'help' to see the list of available commands.
 */
public class UnknownCommand extends Command {

    /**
     * Constructs a new {@code UnknownCommand}.
     * <p>
     * This command is marked as an exit command in the parent {@link Command} class,
     * allowing the application to treat it as a command that may influence program flow.
     */
    public UnknownCommand() {
        super(true);
    }

    /**
     * Executes the unknown command.
     * <p>
     * Returns an error message indicating that the command was not recognized,
     * and suggests using the 'help' command to view available commands.
     *
     * @param storage the storage manager (unused by this command)
     * @param tasks   the current task list (unused by this command)
     * @param ui      the user interface for generating the error message
     * @return a string containing the error message for unknown commands
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.getErrorMessage("Unknown command.\nPlease enter 'help' to see the list of available commands.");
    }
}
