package bubbles;

/**
 * Represents a command to exit the chatbot.
 * When executed, an exit message is displayed.
 */
public class ByeCommand extends Command{
    private String command;

    public ByeCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the exit command and displays an exit message.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }
}

