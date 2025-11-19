package Coffee;

/**
 * Represents the {@code bye} command.
 *
 * This command displays a farewell message to the user and signals that the application
 * should terminate after handling the command.
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     * <p>
     * In addition to the general contract, this implementation shows a farewell message.
     *
     * @param tasks   {@inheritDoc}
     * @param ui      {@inheritDoc}
     * @param storage {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Returns {@code true} to indicate that the application should exit after this command.
     *
     * */
    @Override
    public boolean isExit() {
        return true;
    }
}
