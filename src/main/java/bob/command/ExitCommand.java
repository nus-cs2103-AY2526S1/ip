package bob.command;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    
    /**
     * Constructs ExitCommand.
     */
    public ExitCommand() {
    }

    /**
     * Prints exit message.
     *
     * @param tasks   the TaskList (unused in this command)
     * @param ui      the user interface for output
     * @param storage the storage (unused in this command)
     */
    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        ui.printSection(EXIT_MESSAGE);
    }
}
