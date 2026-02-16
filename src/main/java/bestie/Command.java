package bestie;

/**
 * Base abstraction for commands used by the advanced parser.
 */
public abstract class Command {
    /**
     * Indicates whether the command should terminate the application.
     *
     * @return {@code true} if the command is an exit command
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Runs the command logic.
     *
     * @param tasks    task list to mutate
     * @param ui       UI for presenting messages
     * @param storage  storage layer for persistence
     * @throws BestieException if the command cannot complete successfully
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BestieException;
}
